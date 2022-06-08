package com.example.notbored.presentation.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.databinding.ItemSingleActivityBinding
import com.example.notbored.presentation.view.adapter.OnItemClickListener

class ItemViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
    private val binding = ItemSingleActivityBinding.bind(view)
    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var category: String

    fun bind(category: String, onItemClickListener: OnItemClickListener) {
        binding.categoryTextView.text = category
        this.onItemClickListener = onItemClickListener
        this.category = category

    }

    override fun onClick(v: View?) {
        onItemClickListener.onItemClick(category)
    }
}