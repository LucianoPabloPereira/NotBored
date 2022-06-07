package com.example.notbored.presentation.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.databinding.ItemSingleActivityBinding

class ItemViewholder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSingleActivityBinding.bind(view)

    fun bind(category: String) {
        with(binding) {
            categoryTextView.text = category
        }
    }
}