package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EmployeeActivity : AppCompatActivity() {
    lateinit var register: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        register=findViewById(R.id.register)

        register.setOnClickListener {
            val i = Intent(this, MyprofileActivity::class.java)
            startActivity(i)

        }

    }
}
