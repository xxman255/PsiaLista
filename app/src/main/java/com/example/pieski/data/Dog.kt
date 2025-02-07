package com.example.pieski.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Dog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val owner: String,
    val isFavorite: Boolean = false,
    val imageUrl: String
)
