package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OptionActivity : AppCompatActivity() {

    lateinit var btnEmployer:Button
    lateinit var btnEmployee:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        btnEmployer = findViewById(R.id.btnemployer)
        btnEmployee = findViewById(R.id.btnemployee)

        btnEmployer.setOnClickListener{

            val intent = Intent(this@OptionActivity, EmployerActivity::class.java)
            startActivity(intent)

        }

        btnEmployee.setOnClickListener{

            val intent = Intent(this@OptionActivity, EmployeeActivity::class.java)
            startActivity(intent)

        }
    }
}
