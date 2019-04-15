package com.rafaelguimas.cardata.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelguimas.cardata.R
import kotlinx.android.synthetic.main.item_text.view.*

class SimpleTextListAdapter(
    private val onItemClick: (Pair<String, String>) -> Unit
) : RecyclerView.Adapter<SimpleTextListAdapter.ViewHolder>() {

    private val contentList = HashMap<String, String>()

    fun updateContent(newContentList: HashMap<String, String>) {
        contentList.clear()
        contentList.putAll(newContentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false))
            1 -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text_colored, parent, false))
            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false))
        }
    }

    override fun getItemViewType(position: Int) = position % 2

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(contentList.toList()[position], onItemClick)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pair<String, String>, onItemClick: (Pair<String, String>) -> Unit) {
            itemView.tvItemText.text = item.second

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

}