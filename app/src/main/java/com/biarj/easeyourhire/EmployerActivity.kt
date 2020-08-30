package com.biarj.easeyourhire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmployerActivity : AppCompatActivity() {

    lateinit var recyclerCatDetails: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var catRecyclerAdapter : CatDetailsRecyclerAdapter
    lateinit var btnCreateReq :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer)

        val categories : ArrayList<String> = arrayListOf("Plumber", "electrician", "labour", "carpenter", "mistri", "contractor","sweeper")

        recyclerCatDetails = findViewById(R.id.recyclerEmployer)


            layoutManager = LinearLayoutManager(this@EmployerActivity)

        catRecyclerAdapter = CatDetailsRecyclerAdapter(
            this@EmployerActivity,
            categories
        )

        recyclerCatDetails.adapter = catRecyclerAdapter

        recyclerCatDetails.layoutManager = layoutManager

        btnCreateReq = findViewById(R.id.btnCreateReq)

        btnCreateReq.setOnClickListener{

            val intent = Intent(this@EmployerActivity, RequestActivity::class.java)
            startActivity(intent)
        }


    }
}
