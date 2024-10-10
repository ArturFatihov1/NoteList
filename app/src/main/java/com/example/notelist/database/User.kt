package com.example.notelist.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notelist.constants.ConstantsApp
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ConstantsApp.TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstantsApp.USER_ID)
    val userId: Int?,
    @ColumnInfo(name = ConstantsApp.USER_TITLE)
    var title: String,
    @ColumnInfo(name = ConstantsApp.USER_NOTE)
    var note: String

) : Parcelable