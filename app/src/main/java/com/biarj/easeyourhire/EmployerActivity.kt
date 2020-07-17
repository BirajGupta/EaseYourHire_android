package com.biarj.easeyourhire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmployerActivity : AppCompatActivity() {

    lateinit var recyclerCatDetails: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var catRecyclerAdapter : CatDetailsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer)

        val categories : ArrayList<String> = arrayListOf("Rakshit", "ravish", "arteev", "Biraj")

        recyclerCatDetails = findViewById(R.id.recyclerEmployer)

        layoutManager = LinearLayoutManager(this@EmployerActivity)

        catRecyclerAdapter = CatDetailsRecyclerAdapter(
            this@EmployerActivity,
            categories
        )

        recyclerCatDetails.adapter = catRecyclerAdapter

        recyclerCatDetails.layoutManager = layoutManager



    }
}
