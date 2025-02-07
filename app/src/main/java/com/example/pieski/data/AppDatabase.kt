package com.example.pieski.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
}
