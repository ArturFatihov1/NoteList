package com.example.notelist.user

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notelist.R
import com.example.notelist.database.User
import com.example.notelist.fragments.MainFragmentDirections

class AdapterUser(private val context: Context, private var list: List<User>) :
    RecyclerView.Adapter<AdapterUser.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilteredList: ArrayList<User>) {
        list = newFilteredList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.tv_title)
        val textNote: TextView = itemView.findViewById(R.id.tv_note)
        val noteCard: CardView = itemView.findViewById(R.id.note_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.textTitle.text = data.title
        holder.textNote.text = data.note

        holder.noteCard.setCardBackgroundColor(
            holder.itemView.resources.getColor(
                getRandomColorCard(),
                null
            )
        )

        holder.itemView.rootView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getRandomColorCard(): Int {
        val colorList = ArrayList<Int>()
        val randomNumber = (0..5).random()
        colorList.add(R.color.colorNote1)
        colorList.add(R.color.colorNote2)
        colorList.add(R.color.colorNote3)
        colorList.add(R.color.colorNote4)
        colorList.add(R.color.colorNote5)
        colorList.add(R.color.colorNote6)
        return colorList[randomNumber]
    }
}