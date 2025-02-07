package com.example.pieski.repository

import com.example.pieski.data.Dog
import com.example.pieski.data.DogDao
import com.example.pieski.data.RemoteDogService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(
    private val dogDao: DogDao,
    private val remoteDogService: RemoteDogService
) {
    fun getAllDogs(): Flow<List<Dog>> = dogDao.getAllDogs()

    suspend fun addDog(name: String, owner: String) {
        try {
            val response = remoteDogService.getRandomDogImage()
            if (response.status == "success") {
                val imageUrl = response.message
                val dog = Dog(name = name, owner = owner, imageUrl = imageUrl)
                dogDao.insertDog(dog)
            } else {
                val fallbackUrl = "https://via.placeholder.com/150"
                val dog = Dog(name = name, owner = owner, imageUrl = fallbackUrl)
                dogDao.insertDog(dog)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val fallbackUrl = "https://via.placeholder.com/150"
            val dog = Dog(name = name, owner = owner, imageUrl = fallbackUrl)
            dogDao.insertDog(dog)
        }
    }

    suspend fun updateDog(dog: Dog) {
        dogDao.updateDog(dog)
    }

    suspend fun deleteDog(dog: Dog) {
        dogDao.deleteDog(dog)
    }
}
