package com.callfornation.savetheirsouls.register

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.callfornation.savetheirsouls.otp.OtpVerificationActivity
import com.callfornation.savetheirsouls.R
import com.callfornation.savetheirsouls.dashboard.DashboardActivity
import com.callfornation.savetheirsouls.register.data.FamilyAdapter
import com.callfornation.savetheirsouls.register.data.FamilyMember

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var familyAdapter:FamilyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)
        initializeList()
    }

    private fun initializeList() {
        familyAdapter = FamilyAdapter(this)
        val rvFamilyMembers = findViewById<RecyclerView>(R.id.rvFamily)
        rvFamilyMembers.layoutManager = LinearLayoutManager(this)
        rvFamilyMembers.setHasFixedSize(true)
        ViewCompat.setNestedScrollingEnabled(rvFamilyMembers, false)
        rvFamilyMembers.adapter = familyAdapter
    }

    fun addFamilyMemberClicked(view: View) {
        val addDialog = Dialog(this)
        addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addDialog.setContentView(R.layout.dialog_add_new_member)
        addDialog.findViewById<View>(R.id.btnAdd).setOnClickListener{
            val name = addDialog.findViewById<EditText>(R.id.etName).text.toString()
            val voterId = addDialog.findViewById<EditText>(R.id.etVoterId).text.toString()
            val relation = addDialog.findViewById<EditText>(R.id.etRelation).text.toString()
            val member = FamilyMember(name, voterId, relation)
            familyAdapter.addFamilyMember(member)
            addDialog.dismiss()
        }

        addDialog.findViewById<View>(R.id.btnCancel).setOnClickListener{
            addDialog.dismiss()
        }
        addDialog.show()
    }

    fun doneClicked(view: View) {
        finish()
        startActivity(Intent(this, DashboardActivity::class.java))
    }

}
