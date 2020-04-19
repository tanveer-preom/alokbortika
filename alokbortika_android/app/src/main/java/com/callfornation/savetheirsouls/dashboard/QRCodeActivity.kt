package com.callfornation.savetheirsouls.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import com.callfornation.savetheirsouls.R
import kotlinx.android.synthetic.main.activity_q_r_code.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.app_bar_dashboard.toolbar
import net.glxn.qrgen.android.QRCode


class QRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_code)
        setSupportActionBar(toolbar)
        initQrCode()
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    private fun initQrCode() {
        val ivQrCode = findViewById<ImageView>(R.id.ivQrCode)
        val bitmap = QRCode.from("testStr").bitmap()
        ivQrCode.setImageBitmap(bitmap)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }
}
