package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            val startAct = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(startAct)
            finish()
        },2000)
        }
    }


