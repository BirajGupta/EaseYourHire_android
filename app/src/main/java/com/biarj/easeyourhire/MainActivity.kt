package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var enterusername:EditText
    lateinit var contactinfo:EditText
    lateinit var loginb:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterusername=findViewById(R.id.enterusername)
        contactinfo=findViewById(R.id.contactinfo)
        loginb = findViewById(R.id.loginb)
        loginb.setOnClickListener{
            val i = Intent(this, OptionActivity::class.java)
            startActivity(i)


}
    }
}
