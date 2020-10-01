package com.biarj.easeyourhire

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_employer.*
import org.json.JSONException

class EmployerActivity : AppCompatActivity() {

    lateinit var recyclerCatDetails: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var catRecyclerAdapter : CatDetailsRecyclerAdapter
    lateinit var btnCreateReq :Button
lateinit var pl:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer)
         pl=findViewById(R.id.pl)

        recyclerCatDetails = findViewById(R.id.recyclerEmployer)
var workerlist=ArrayList<Workers>()

            layoutManager = LinearLayoutManager(this@EmployerActivity)

           pl.visibility = View.VISIBLE
        val que = Volley.newRequestQueue(this)
        val url = "https://d27f8e5f6f78.ngrok.io/eyhdb/sendWorkerdata.php"
        if (ConnectionManager().checkconnectivity(this)) {
            val jsonObjectRequest =
                object : JsonObjectRequest(Method.GET, url, null, Response.Listener {
                    try {
                        pl.visibility = View.GONE

                        val boolean = it.getBoolean("success")
                        println(boolean)
                        if (boolean) {
                            val data = it.getJSONArray("data")
                            println(data)
                            for (i in 0 until data.length()) {
                                val jsonObject = data.getJSONObject(i)
                                println("reached")
                                val workers=Workers(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("fullname"),
                                    jsonObject.getString("phone"),
                                    jsonObject.getString("Images")
                                )
                                workerlist.add(workers)

                                println(workers)


                                catRecyclerAdapter = CatDetailsRecyclerAdapter(this, workerlist)


                                recyclerCatDetails.adapter = catRecyclerAdapter
                                recyclerCatDetails.layoutManager = layoutManager

                            }
                        } else {
                            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                        }

                    } catch (e: JSONException) {
                        Toast.makeText(this, "ERROR 1213", Toast.LENGTH_LONG).show()
                    }

                }, Response.ErrorListener {
                    Toast.makeText(this, "VOLLEY ERROR", Toast.LENGTH_LONG).show()
                }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-type"] = "application/json"
                        return headers
                    }

                }
            que.add(jsonObjectRequest)
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



        btnCreateReq = findViewById(R.id.btnCreateReq)

        btnCreateReq.setOnClickListener{

            val intent = Intent(this@EmployerActivity, RequestActivity::class.java)
            startActivity(intent)
        }


    }
}
