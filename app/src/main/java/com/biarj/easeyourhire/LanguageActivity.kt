package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LanguageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        Handler().postDelayed({
            val startAct = Intent(this@LanguageActivity, MainActivity::class.java)
            startActivity(startAct)
            finish()
        },2000)
        }
    }


