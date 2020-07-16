package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_option.*

class OptionActivity : AppCompatActivity() {
    lateinit var employer: Button
    lateinit var employee: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
        employer=findViewById(R.id.employer)
        employee=findViewById(R.id.employee)
        employer.setOnClickListener{
            val i = Intent(this, EmployerActivity::class.java)
            startActivity(i)


        }
        employee.setOnClickListener{
            val i = Intent(this, EmployeeActivity::class.java)
            startActivity(i)


        }
    }
}
