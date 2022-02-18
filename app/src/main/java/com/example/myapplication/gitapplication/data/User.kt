package com.example.myapplication.gitapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String?,
    val surname: String?,
    val message: String?,
    val time: String?,
    var favorite: Boolean
) : Parcelable
