package com.callfornation.savetheirsoulsvolunteer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class NumberInputActivity : AppCompatActivity() {
    private val REQUEST_SMS_PERMISSION = 1000
    companion object {
        var type: Type = Type.LOGIN
        fun startActivity(context: Context, type: Type) {
            this.type = type
            context.startActivity(Intent(context, NumberInputActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun proceedClicked(view : View) {
        checkReadSmsPermission()
    }

    private fun checkReadSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_SMS), REQUEST_SMS_PERMISSION)
        } else {
            verifyNumber()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_SMS_PERMISSION -> {
                verifyNumber()
            }
        }
    }

    private fun verifyNumber() {
        val phoneNumber = "+88"+ findViewById<EditText>(R.id.phoneNumber).text.toString()
        OtpVerificationActivity.startOtpVerification(this, phoneNumber)
    }
    enum class Type{
        LOGIN, REGISTRATION
    }
}
