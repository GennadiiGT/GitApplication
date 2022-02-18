package com.example.myapplication.gitapplication.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.gitapplication.data.User
import java.util.*

class StartViewModel : ViewModel() {

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> = _users

    private val _navigateToSelectedUser = MutableLiveData<User?>()
    val navigateToSelectedUser: LiveData<User?> = _navigateToSelectedUser

    init {
        _users.value = getUsers(20)

    }

    private fun getUsers(count: Int): MutableList<User> {
        val list = mutableListOf<User>()
        (0..count).forEach {
            val random = (2..50).random()
            list.add(
                User(
                    UUID.randomUUID().toString().take(5),
                    UUID.randomUUID().toString().take(10),
                    UUID.randomUUID().toString().take(random),
                    UUID.randomUUID().toString().take(5),
                    false
                )
            )
        }
        return list
    }

    fun removeUser(position: Int) {
        _users.value?.removeAt(position)
    }

    fun displayPropertyDetails(user: User) {
        _navigateToSelectedUser.value = user
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedUser.value = null
    }

    companion object {
        const val SIZE = 200
        const val PATH = "https://brokenfortest"
    }

}