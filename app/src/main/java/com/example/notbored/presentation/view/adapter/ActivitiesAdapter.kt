package com.example.notbored.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.R
import com.example.notbored.presentation.view.viewholder.ItemViewholder

class ActivitiesAdapter(private val categoryList: List<String>) : RecyclerView.Adapter<ItemViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewholder(
            layoutInflater.inflate(R.layout.item_single_activity, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size
}