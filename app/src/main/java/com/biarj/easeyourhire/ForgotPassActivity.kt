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

class ForgotPassActivity : AppCompatActivity() {
    lateinit var etmobile: EditText

    lateinit var next: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        title = "Forgot Password"
        etmobile = findViewById(R.id.mobile)

        next = findViewById(R.id.next)
        next.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "http://13.235.250.119/v2/forgot_password/fetch_result"
            val jsonParams = JSONObject()
            val mobile = etmobile.text.toString()

            jsonParams.put("mobile_number", mobile)

            if (Validations.validateMobile(mobile)) {

                    if (ConnectionManager().checkconnectivity(this)) {
                        val jsonRequest =
                            object : JsonObjectRequest(
                                Method.POST, url, jsonParams, Response.Listener {
                                    try {
                                        val data = it.getJSONObject("data")
                                        val success = data.getBoolean("success")

                                        if (success) {

                                            val i =
                                                Intent(this, OtpVerifyActivity::class.java)
                                            i.putExtra("mobile_number", mobile)
                                            startActivity(i)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Wrong Login Credentials",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } catch (e: JSONException) {
                                        Toast.makeText(this, "Error1212", Toast.LENGTH_SHORT).show()

                                    }
                                },
                                Response.ErrorListener {
                                    Toast.makeText(
                                        this,
                                        "Volley Error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            ) {
                                override fun getHeaders(): MutableMap<String, String> {
                                    val headers = HashMap<String, String>()
                                    headers["Content-Type"] = "application/json"

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
                }
            else {
                etmobile.error = "Enter your correct mobile number"

            }
        }
    }}
