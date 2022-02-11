package com.example.myapplication.gitapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.gitapplication.R
import com.example.myapplication.gitapplication.data.User
import com.example.myapplication.gitapplication.databinding.RvItemBinding

class UserAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {

            return oldItem.name == newItem.name
        }

    }

    class UserViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isOn = false
        fun setUser(userAdd: User) = with(binding) {
            user = userAdd
            imageStar.setOnClickListener {
                if (isOn) {
                    imageStar.setImageResource(R.drawable.ic_baseline_star_outline_24)
                } else {
                    imageStar.setImageResource(R.drawable.ic_baseline_star_rate_24)
                }
                isOn = !isOn
            }
            executePendingBindings()
        }
    }


    class OnClickListener(val clickListener: (user: User) -> Unit) {
        fun onClick(user: User) = clickListener(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(userProperty)
        }
        holder.setUser(userProperty)
    }
}