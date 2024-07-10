package com.example.notelist.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notelist.database.User
import com.example.notelist.database.UserDAO
import kotlinx.coroutines.launch

class UserViewModel(private val dao: UserDAO) : ViewModel() {
    val users = dao.getAllUsers()


    fun insert(user: User) {
        viewModelScope.launch {
            dao.insert(user)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            dao.deleteAll()
        }
    }
}