package com.biarj.easeyourhire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RequestsActivity : AppCompatActivity() {

    lateinit var recyclerReqDetails: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var ReqRecyclerAdapter : ReqDetailsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requests)

        val requests : ArrayList<ReqDetails> = arrayListOf(ReqDetails("Rakshit","Sweepeer"),ReqDetails("biraj","malik"))

        recyclerReqDetails = findViewById(R.id.recyclerEmpReq)

        layoutManager = LinearLayoutManager(this@RequestsActivity)

        ReqRecyclerAdapter = ReqDetailsRecyclerAdapter(
            this@RequestsActivity,
            requests
        )

        recyclerReqDetails.adapter = ReqRecyclerAdapter

        recyclerReqDetails.layoutManager = layoutManager

    }
}
