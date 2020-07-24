package com.biarj.easeyourhire

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ReqDetailsRecyclerAdapter(val context: Context, var requests:ArrayList<ReqDetails>) : RecyclerView.Adapter<ReqDetailsRecyclerAdapter.ReqDetailsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReqDetailsRecyclerAdapter.ReqDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_single_empreq_activity,parent, false)


        return ReqDetailsRecyclerAdapter.ReqDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onBindViewHolder(
        holder: ReqDetailsViewHolder,
        position: Int
    ) {

            val req = requests[position]

            holder.txtEmpName.text = req.name
            holder.txtJobType.text = req.jobtype

            holder.btnReqApply.setOnClickListener{

                Toast.makeText(context, "applied", Toast.LENGTH_SHORT).show()
            }
    }

    class ReqDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var btnReqApply: Button = view.findViewById(R.id.btnReqApply)
        var txtEmpName: TextView = view.findViewById(R.id.txtEmpName)
        var txtJobType: TextView = view.findViewById(R.id.txtJobType)

    }
}