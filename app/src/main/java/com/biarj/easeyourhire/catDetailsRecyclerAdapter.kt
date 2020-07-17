package com.biarj.easeyourhire

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatDetailsRecyclerAdapter(context: Context, var categories:ArrayList<String>): RecyclerView.Adapter<CatDetailsRecyclerAdapter.ResDetailsViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatDetailsRecyclerAdapter.ResDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employer_singlecat_recycler,parent, false)


        return ResDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(
        holder: CatDetailsRecyclerAdapter.ResDetailsViewHolder,
        position: Int
    ) {
        val category = categories[position]

        holder.workerName.text = category
    }


    class ResDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view){

            var workerName: Button = view.findViewById(R.id.btnWorkerCat)

    }
}