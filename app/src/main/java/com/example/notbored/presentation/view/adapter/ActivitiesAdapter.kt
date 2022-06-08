package com.example.notbored.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.R
import com.example.notbored.presentation.view.viewholder.ItemViewHolder

class ActivitiesAdapter(
    private val categoryList: List<String>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.item_single_activity, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(categoryList[position], onItemClickListener)
    }

    override fun getItemCount() = categoryList.size
}