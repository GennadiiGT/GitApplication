package com.example.myapplication.gitapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.example.myapplication.gitapplication.data.User
import com.squareup.picasso.Picasso
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.example.myapplication.gitapplication.R
import com.example.myapplication.gitapplication.ui.StartViewModel.Companion.PATH
import com.example.myapplication.gitapplication.ui.StartViewModel.Companion.SIZE
import com.example.myapplication.gitapplication.ui.adapters.UserAdapter


@BindingAdapter("listData")
fun bindingRecyclerView(recyclerView: RecyclerView, data: MutableList<User>?) {
    val adapter = recyclerView.adapter as UserAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageView")
fun bindingImageView(imageView: ImageView, name: String) {
    Picasso.get()
        .load(PATH)
        .placeholder(
            AvatarGenerator.avatarImage(
                imageView.context,
                SIZE,
                AvatarConstants.CIRCLE,
                name.take(1)
            )
        )
        .into(imageView)
}

@BindingAdapter("imageViewStar")
fun imageStarsetOnClick(imageView: ImageView, user: User) {
    if (!user.favorite) {
        imageView.setImageResource(R.drawable.ic_baseline_star_outline_24)
    } else {
        imageView.setImageResource(R.drawable.ic_baseline_star_rate_24)
    }
}
