package com.rafaelguimas.cardata.util

import androidx.recyclerview.widget.DiffUtil

class PairStringDiffCallback : DiffUtil.ItemCallback<Pair<String, String>>() {

    override fun areContentsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean {
        return oldItem.first == newItem.first && oldItem.second == newItem.second
    }

    override fun areItemsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean {
        return oldItem == newItem
    }

}