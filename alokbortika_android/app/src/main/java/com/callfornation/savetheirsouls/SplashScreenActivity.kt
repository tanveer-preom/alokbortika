package com.callfornation.savetheirsouls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import java.lang.Thread.sleep

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        FirebaseApp.initializeApp(applicationContext)
        Thread(Runnable {
            sleep(2500)
            finish()
            startActivity(Intent(this, LoginOrRegistrationActivity::class.java))
        }).start()
    }
}
