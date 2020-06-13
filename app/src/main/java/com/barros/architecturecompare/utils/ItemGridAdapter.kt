package com.barros.architecturecompare.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barros.architecturecompare.databinding.GridItemBinding
import com.barros.architecturecompare.model.RedditItem

class ItemGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<RedditItem, ItemGridAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class ViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RedditItem) {
            binding.redditItem = item
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (item: RedditItem) -> Unit) {
        fun onClick(item: RedditItem) = clickListener(item)
    }
}

class DiffCallback : DiffUtil.ItemCallback<RedditItem>() {
    override fun areItemsTheSame(oldItem: RedditItem, newItem: RedditItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RedditItem, newItem: RedditItem): Boolean {
        return oldItem == newItem
    }
}
