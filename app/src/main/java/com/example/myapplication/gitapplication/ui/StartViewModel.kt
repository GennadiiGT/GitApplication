package com.example.myapplication.gitapplication.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.gitapplication.data.User
import java.util.*

class StartViewModel:ViewModel() {

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> = _users

    init {
        _users.value = getUsers(20)
    }

    private fun getUsers(count: Int): MutableList<User> {
        val list = mutableListOf<User>()
        (0..count).forEach {
            list.add(
                User(
                    UUID.randomUUID().toString().take(5),
                    UUID.randomUUID().toString().take(20),
                    UUID.randomUUID().toString().take(20),
                    UUID.randomUUID().toString().take(5),
                )
            )
        }
        return list
    }

    companion object {
        const val SIZE = 200
        const val PATH = "https://brokenfortest"
    }



}