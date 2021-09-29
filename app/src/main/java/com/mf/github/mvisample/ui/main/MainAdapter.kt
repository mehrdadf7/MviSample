package com.mf.github.mvisample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mf.github.mvisample.data.model.User
import com.mf.github.mvisample.databinding.ItemUserBinding

class MainAdapter : ListAdapter<User, MainAdapter.DataViewHolder>(
    object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return newItem == oldItem
        }
    }
) {

    inner class DataViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            name.text = user.name
            email.text = user.email
            Glide.with(itemView.context)
                .load(user.avatar)
                .into(avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}