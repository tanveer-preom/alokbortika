package com.callfornation.savetheirsoulsvolunteer

import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.SmsMessage
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_otp_verification.*
import java.util.concurrent.TimeUnit


class OtpVerificationActivity : AppCompatActivity() {
    private var verificationId = ""
    companion object {
        var phoneNumber: String = ""
        fun startOtpVerification(context: Context, phoneNumber: String) {
            this.phoneNumber = phoneNumber
            context.startActivity(Intent(context, OtpVerificationActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        FirebaseApp.initializeApp(applicationContext)
        initView()
        registerReceiver(firebaseMessageReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
        sendAndProcessOtp(phoneNumber)
    }

    private fun initView() {
        otpCodeView.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length == 6) {
                    verifyOtp(s.toString())
                }
            }

        })
    }

    private fun verifyOtp(otp: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        signInWithPhoneAuthCredential(credential)
    }

    private fun sendAndProcessOtp(phoneNumber: String) {
        val verificationCallback = object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) { // This callback will be invoked in two situations:
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) { // This callback is invoked in an invalid request for verification is made,
                e.printStackTrace()
            }

            override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
                this@OtpVerificationActivity.verificationId = verificationId
            }
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,        // Phone number to verify
            60,                 // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            this,               // Activity (for callback binding)
            verificationCallback)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val dialog = ProgressDialog.show(this, getString(R.string.pleaseWait), getString(R.string. verifyingOtp))
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val user = task.result!!.user
                        dialog.dismiss()
                        finish()
                        startActivity(Intent(this, DashboardActivity::class.java))
                    } else {
                        Log.w(
                            "FirebaseAuth", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, R.string.invalidOtp, Toast.LENGTH_LONG).show()
                            dialog.dismiss()
                        }
                    }
                })
    }


    fun proceedClicked(view: View) {
        if(otpCodeView.text.toString().length == 6) {
            verifyOtp(otpCodeView.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(firebaseMessageReceiver)
    }

    private val firebaseMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
                val extras = intent.extras
                if (extras != null) {
                    try {
                        val smsExtra =
                            extras["pdus"] as Array<Any>? ?: return
                        val sms =
                            SmsMessage.createFromPdu(smsExtra[0] as ByteArray)
                        val address = sms.originatingAddress
                        var body = sms.messageBody
                        if (body.contains(getString(R.string.firebase_sms_string)))
                        {
                            body = sms.messageBody
                            body = body.replace("\\D+".toRegex(), "")
                            otpCodeView.setText(body)
                            verifyOtp(body)
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }


}
