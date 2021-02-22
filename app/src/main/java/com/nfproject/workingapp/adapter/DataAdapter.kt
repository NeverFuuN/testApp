package com.nfproject.workingapp.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nfproject.workingapp.R
import com.nfproject.workingapp.models.databaseModel
import kotlinx.android.synthetic.main.rv_items.view.*

class dataAdapter(private var dblist: ArrayList<databaseModel>) :
    RecyclerView.Adapter<dataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.tv_name
        var email: TextView = itemView.tv_email
        var msg: TextView = itemView.tv_msg
        var btnDel = itemView.btn_delete
        var btnEdt = itemView.btn_edt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dblist[position].name
        holder.email.text = dblist[position].email
        holder.msg.text = dblist[position].msg
    }

    override fun getItemCount(): Int {
        return dblist.size
    }
}