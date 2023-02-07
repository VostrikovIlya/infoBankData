package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.Data



@Database(entities = [Data::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): DataRoomDao
}