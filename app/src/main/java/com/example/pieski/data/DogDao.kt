package com.example.pieski.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    @Query("SELECT * FROM dogs")
    fun getAllDogs(): Flow<List<Dog>>

    @Insert
    suspend fun insertDog(dog: Dog): Long

    @Update
    suspend fun updateDog(dog: Dog)

    @Delete
    suspend fun deleteDog(dog: Dog)
}
