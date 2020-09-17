package com.biarj.easeyourhire


import android.content.Context

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var pass: EditText
    lateinit var contactinfo: EditText
    lateinit var loginb: Button
lateinit var fp:TextView
    lateinit var sharedPreferences: SharedPreferences

    lateinit var noacc: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getSharedPreferences("eyh", Context.MODE_PRIVATE)


        fp=findViewById(R.id.fp)
        pass = findViewById(R.id.pass)
        contactinfo = findViewById(R.id.phone)
        loginb = findViewById(R.id.loginb)
        noacc=findViewById(R.id.noacc)
        loginb.setOnClickListener {

            val queue = Volley.newRequestQueue(this)

            val url = "https://7bf224560da6.ngrok.io/eyhdb/login_validation.php"

            val jsonParams = JSONObject()
            val mobile="+91${contactinfo.text.toString()}"
            val pass=pass.text.toString()
            jsonParams.put("phone",mobile)
            jsonParams.put("pass",pass)
            val jsonRequest =
            object : JsonObjectRequest(Method.POST, url, jsonParams, Response.Listener {
                try {val success=it.getBoolean("success")
                    if(success) {
                        val type = sharedPreferences.getString("type","")
                        if(type == "employee")
                        {
                            val i = Intent(this, EmployeeActivity::class.java)
                            startActivity(i)
                        }
                        else if(type == "employer") {
                            val i = Intent(this, EmployerActivity::class.java)
                            startActivity(i)
                        }
                    }
                    else{
                        Toast.makeText(this, "Wrong Login Credentials", Toast.LENGTH_SHORT).show()
                    }
                } catch (e:JSONException) {
                    Toast.makeText(this, "Error1212", Toast.LENGTH_SHORT).show()

                }
            },
                Response.ErrorListener {   Toast.makeText(this, "Volley Error", Toast.LENGTH_SHORT).show() }
                ){ override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"

                return headers
            }





        }
            queue.add(jsonRequest)
    }
        noacc.setOnClickListener {

            val j = Intent(this, RegistrationActivity::class.java)
            startActivity(j)
        }
        fp.setOnClickListener {

            val j = Intent(this,ForgotPassActivity::class.java)
            startActivity(j)
        }
}
}
