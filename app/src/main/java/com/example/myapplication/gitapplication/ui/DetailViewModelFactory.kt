package com.example.myapplication.gitapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.gitapplication.data.User

class DetailViewModelFactory(
    private val user: User
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(user) as T
        }
        throw IllegalAccessException("Unknown ViewModel $modelClass")
    }
}