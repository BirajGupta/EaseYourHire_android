package com.biarj.easeyourhire

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity() {
    lateinit var etname: EditText
    lateinit var toolbar: Toolbar
    lateinit var etmobile: EditText
    lateinit var etage: EditText

    lateinit var etpassword: EditText
    lateinit var etconpass: EditText
    lateinit var register: Button
    lateinit var sp: SharedPreferences
    lateinit var spinner1:Spinner
    lateinit var spinner2:Spinner
    lateinit var spinner3:Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        title = "Register Yourself"
        sp = getSharedPreferences("eyh", Context.MODE_PRIVATE)

        etname = findViewById(R.id.name)
        etmobile = findViewById(R.id.mobile)
        etage = findViewById(R.id.age)

        etpassword = findViewById(R.id.pass)
        etconpass = findViewById(R.id.conpass)
        register = findViewById(R.id.register)
       spinner1 =  findViewById(R.id.occupation1)
        spinner2 =  findViewById(R.id.occupation2)
        spinner3 =  findViewById(R.id.occupation3)
        val type = sp.getString("type","")
        if(type == "employer")
        {spinner1.visibility=View.GONE
            spinner2.visibility=View.GONE
            spinner3.visibility=View.GONE 

        }
        register.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val url = "https://0579498d8098.ngrok.io/eyhdb/signupempee.php"
            val jsonParams = JSONObject()
            val name = etname.text.toString()
            val mobile = etmobile.text.toString()
            val pass = etpassword.text.toString()
            val occupation1 = spinner1.selectedItem.toString()
            val occupation2 = spinner2.selectedItem.toString()
            val occupation3 = spinner3.selectedItem.toString()
            val age = etage.text.toString()
            jsonParams.put("phone", mobile)
            jsonParams.put("fullname", name)
            if (type == "employee") {
                jsonParams.put("occupation", occupation1)
                jsonParams.put("occupation1", occupation2)
                jsonParams.put("occupation2", occupation3)
            }
            jsonParams.put("age", age)
            jsonParams.put("pass", pass)
            if (Validations.validateNameLength(name)) {

                if (etage != null) {

                    if (Validations.validateMobile(mobile)) {
                        if (true) {
                            if (Validations.validatePasswordLength(pass)) {

                                if (Validations.matchPassword(pass, etconpass.text.toString())) {


                                    if (ConnectionManager().checkconnectivity(this)) {
                                        val jsonRequest =
                                            object : JsonObjectRequest(
                                                Method.POST,
                                                url,
                                                jsonParams,
                                                Response.Listener {
                                                    try {

                                                        val success =
                                                            it.getBoolean("success")
                                                        println("******************************")
                                                        println(success)

                                                        if (success) {
                                                            println("in apisuccess")
                                                            val data1 =
                                                                it.getString("message")

                                                            Toast.makeText(
                                                                this,
                                                                "REGISTRATION SUCCESS",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                            val i = Intent(
                                                                this,
                                                                OptionActivity::class.java
                                                            )
                                                            startActivity(i)
                                                            finish()
                                                        } else {
                                                            println("in apifalse")
                                                            Toast.makeText(
                                                                this,
                                                                "SOMETHING WENT WRONG",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                    } catch (e: JSONException) {
                                                        Toast.makeText(
                                                            this,
                                                            "Error1212",
                                                            Toast.LENGTH_SHORT
                                                        ).show()

                                                    }
                                                },
                                                Response.ErrorListener { }) {
                                                override fun getHeaders(): MutableMap<String, String> {
                                                    val headers = HashMap<String, String>()
                                                    headers["Content-Type"] =
                                                        "application/json"

                                                    return headers
                                                }
                                            }
                                        queue.add(jsonRequest)
                                    } else {

                                        val alert = AlertDialog.Builder(this)
                                        alert.setTitle("Error")
                                        alert.setMessage("INTERNET connection not found")
                                        alert.setPositiveButton("open settings") { text, listener ->
                                            val i = Intent(Settings.ACTION_WIFI_SETTINGS)
                                            startActivity(i)
                                            this?.finish()


                                        }
                                        alert.setNegativeButton("exit") { text, listener ->
                                            ActivityCompat.finishAffinity(this)

                                        }
                                        alert.create().show()


                                    }
                                } else {
                                    etpassword.error = "password dont match"
                                    etconpass.error = "password dont match"

                                }
                            } else {
                                etpassword.error = "password should be more than of length 4 "
                            }
                        } else {//etoccupation.error="can't be empty"

                        }
                    }else
                         {
                        etmobile.error = "Invalid mobile number"

                    }
                } else {
                    etage.error = "fill your age"
                }

            } else {
                etname.error = "Name should be of atleast 3 characters"
            }
        }
    }

}
