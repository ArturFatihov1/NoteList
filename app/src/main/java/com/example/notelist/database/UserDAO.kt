package com.example.notelist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notelist.constants.ConstantsApp

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM ${ConstantsApp.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${ConstantsApp.TABLE_NAME}")
    fun getAllUsers(): LiveData<List<User>>
}