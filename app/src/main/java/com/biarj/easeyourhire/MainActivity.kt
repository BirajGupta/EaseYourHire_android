package com.biarj.easeyourhire

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var enterusername: EditText
    lateinit var contactinfo: EditText
    lateinit var loginb: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("eyh", Context.MODE_PRIVATE)

        enterusername = findViewById(R.id.enterusername)
        contactinfo = findViewById(R.id.contactinfo)
        loginb = findViewById(R.id.loginb)
        loginb.setOnClickListener {

            val type = sharedPreferences.getString("type","")

            if(type == "employee") {
                val i = Intent(this, EmployeeActivity::class.java)
                startActivity(i)
            }else if(type == "employer") {
                val i = Intent(this, EmployerActivity::class.java)
                startActivity(i)
            }

        }
    }
}
