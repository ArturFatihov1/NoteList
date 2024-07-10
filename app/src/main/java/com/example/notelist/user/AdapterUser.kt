package com.example.notelist.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notelist.R
import com.example.notelist.database.User

class AdapterUser(private val list: List<User>) : RecyclerView.Adapter<AdapterUser.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView = itemView.findViewById(R.id.tv_title)
        val tv_note: TextView = itemView.findViewById(R.id.tv_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_title.text = list[position].note
        holder.tv_note.text = list[position].title
    }

    override fun getItemCount(): Int {
        return list.size
    }


}