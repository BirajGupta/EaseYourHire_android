package com.biarj.easeyourhire

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OptionActivity : AppCompatActivity() {

    lateinit var btnEmployer:Button
    lateinit var btnEmployee:Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        sharedPreferences = getSharedPreferences("eyh", Context.MODE_PRIVATE)

        btnEmployer = findViewById(R.id.btnemployer)
        btnEmployee = findViewById(R.id.btnemployee)

        btnEmployer.setOnClickListener{

            val intent = Intent(this@OptionActivity, MainActivity::class.java)
            startActivity(intent)
            sharedPreferences.edit().putString("type","employer").apply()

        }

        btnEmployee.setOnClickListener{

            val intent = Intent(this@OptionActivity, MainActivity::class.java)
            startActivity(intent)
            sharedPreferences.edit().putString("type","employee").apply()

        }
    }
}
