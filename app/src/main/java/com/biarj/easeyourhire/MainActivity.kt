package com.biarj.easeyourhire

<<<<<<< Updated upstream
import android.content.Context
=======
import android.annotation.SuppressLint
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    lateinit var sharedPreferences: SharedPreferences
=======
    lateinit var noacc: TextView
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< Updated upstream

        sharedPreferences = getSharedPreferences("eyh", Context.MODE_PRIVATE)

        enterusername = findViewById(R.id.enterusername)
        contactinfo = findViewById(R.id.contactinfo)
=======
       pass = findViewById(R.id.pass)
        contactinfo = findViewById(R.id.phone)
>>>>>>> Stashed changes
        loginb = findViewById(R.id.loginb)
        noacc=findViewById(R.id.noacc)
        loginb.setOnClickListener {
<<<<<<< Updated upstream
=======
            val queue = Volley.newRequestQueue(this)
            val url = "http://9232ff46a983.ngrok.io/eyhdb/login_validation.php"
            val jsonParams = JSONObject()
            val mobile=contactinfo.text.toString()
            val pass=pass.text.toString()
            jsonParams.put("phone",mobile)
            jsonParams.put("pass",pass)
            val jsonRequest =
            object : JsonObjectRequest(Method.POST, url, jsonParams, Response.Listener {
                try {val success=it.getBoolean("success")
                    if(success) {
                        val i = Intent(this, OptionActivity::class.java)
                        startActivity(i)
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
>>>>>>> Stashed changes

            val type = sharedPreferences.getString("type","")

            if(type == "employee") {
                val i = Intent(this, EmployeeActivity::class.java)
                startActivity(i)
            }else if(type == "employer") {
                val i = Intent(this, EmployerActivity::class.java)
                startActivity(i)
            }

        }
            queue.add(jsonRequest)
    }
        noacc.setOnClickListener {

            val j = Intent(this, RegistrationActivity::class.java)
            startActivity(j)
        }
}
}
