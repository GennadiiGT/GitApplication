package com.example.myapplication.gitapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.gitapplication.data.User

class DetailViewModel(user: User) : ViewModel() {

    private val _selectedUser = MutableLiveData<User>()
    val selectedUser: LiveData<User>
        get() = _selectedUser

    init {
        _selectedUser.value = user
    }

}