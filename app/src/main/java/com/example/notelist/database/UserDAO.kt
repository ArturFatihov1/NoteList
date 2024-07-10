package com.example.notelist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notelist.constants.ConstantsApp

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM ${ConstantsApp.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${ConstantsApp.TABLE_NAME}")
    fun getAllUsers(): LiveData<List<User>>
}