package com.example.myapplication.gitapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.example.myapplication.gitapplication.data.User
import com.squareup.picasso.Picasso
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.example.myapplication.gitapplication.ui.StartViewModel.Companion.PATH
import com.example.myapplication.gitapplication.ui.StartViewModel.Companion.SIZE


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


