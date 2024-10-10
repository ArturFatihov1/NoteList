package com.example.notelist.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notelist.database.User
import com.example.notelist.database.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val dao: UserDAO) : ViewModel() {
    val users = dao.getAllUsers()

    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(user)
        }
    }

    fun update(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(user)
        }
    }

}