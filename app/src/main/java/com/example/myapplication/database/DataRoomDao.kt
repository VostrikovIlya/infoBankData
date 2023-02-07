package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.Data


@Dao
interface DataRoomDao {
    @Query("Select * from data")
    suspend fun getAllData() : List<Data>

    @Insert
    suspend fun addData(data: Data)

    @Delete
    suspend fun removeData(data: Data)
}