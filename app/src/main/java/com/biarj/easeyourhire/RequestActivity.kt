package com.biarj.easeyourhire

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Method


class RequestActivity : AppCompatActivity() {
    lateinit var etname:EditText
    lateinit var etmobile:EditText
    lateinit var etlocation:EditText
    lateinit var etreq:EditText
    lateinit var submit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)
        title="REQUEST GENERATION"
        etname=findViewById(R.id.txtname)
        etmobile=findViewById(R.id.txtmobile)
        etlocation=findViewById(R.id.txtlocation)
        etreq=findViewById(R.id.txtrequirement)
        submit=findViewById(R.id.btnsubmit)
        submit.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://d27f8e5f6f78.ngrok.io/eyhdb/requests.php"
            val jsonParams = JSONObject()
            val mobile=etmobile.text.toString()
            val name=etname.text.toString()
            val location=etlocation.text.toString()
            val req=etreq.text.toString()
            jsonParams.put("phone",mobile)
            jsonParams.put("fullname" ,name)
            jsonParams.put("location" ,location)
            jsonParams.put("requirement" ,req)
            if(Validations.validateMobile(mobile)&& Validations.validateNameLength(name)&& etreq!=null && etlocation!=null){
                if (ConnectionManager().checkconnectivity(this)){
                    val jsonRequest =
                        object : JsonObjectRequest(
                            Method.POST, url, jsonParams, Response.Listener {
                                try {

                                    val success=it.getBoolean("success")

                                    if(success) {
                                        Toast.makeText(this, "Request Added", Toast.LENGTH_SHORT).show()


                                        val i = Intent(this,RequestsActivity::class.java)
                                        startActivity(i)
                                        finish()
                                    }
                                    else{
                                        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: JSONException) {
                                    Toast.makeText(this, "Error1212", Toast.LENGTH_SHORT).show()

                                }
                            },
                            Response.ErrorListener {   Toast.makeText(this, "Volley Error", Toast.LENGTH_SHORT).show() }
                        ){
                            override fun getHeaders(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Content-Type"] = "application/json"

                            return headers
                        }


                        }
                    queue.add(jsonRequest)
                }
                else{
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Error")
                    alert.setMessage("INTERNET connection not found")
                    alert.setPositiveButton("open settings") { text, listener ->
                        val i= Intent(Settings.ACTION_WIFI_SETTINGS)
                        startActivity(i)
                        this?.finish()


                    }
                    alert.setNegativeButton("exit") { text, listener ->
                        ActivityCompat.finishAffinity(this)

                    }
                    alert.create().show()



                }}
            else{
                Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        }

    }
