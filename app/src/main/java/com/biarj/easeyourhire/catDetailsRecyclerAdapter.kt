package com.biarj.easeyourhire

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CatDetailsRecyclerAdapter(val context: Context, var workerlist: ArrayList<Workers>): RecyclerView.Adapter<CatDetailsRecyclerAdapter.ResDetailsViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatDetailsRecyclerAdapter.ResDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employer_single_recycler, parent, false)


        return ResDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workerlist.size
    }

    override fun onBindViewHolder(
        holder: CatDetailsRecyclerAdapter.ResDetailsViewHolder,
        position: Int
    ) {
        val w = workerlist[position]

        holder.workerName.text = w.workername
        holder.workerphoneno.text = w.workerphoneno
        Picasso.get().load(w.workerimg).error(R.drawable.profile).into(holder.workerImage)
        holder.workerphoneno.setOnClickListener{



            val u: Uri = Uri.parse("tel:" + holder.workerphoneno.getText().toString())
            val i = Intent(Intent.ACTION_DIAL, u)

            try {

                context.startActivity(i)
            } catch (s: SecurityException) {

                Toast.makeText(context, "some error occured", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    class ResDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var workerName: TextView = view.findViewById(R.id.txtPersonName)
        var workerphoneno: TextView = view.findViewById(R.id.txtPersonNumber)
        var workerMerit: TextView = view.findViewById(R.id.txtPersonMerit)
        var workerImage: ImageView = view.findViewById(R.id.imgPersonImage)





    }
}