package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MyProfileActivity : AppCompatActivity() {

    lateinit var btnRequest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        btnRequest = findViewById(R.id.btnRequestsPage)


        btnRequest.setOnClickListener{

            val intent = Intent(this@MyProfileActivity, RequestsActivity::class.java)
            startActivity(intent)
        }

    }
}
