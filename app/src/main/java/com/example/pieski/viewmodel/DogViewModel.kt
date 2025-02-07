package com.example.pieski.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pieski.data.Dog
import com.example.pieski.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    val dogs: Flow<List<Dog>> = repository.getAllDogs()

    fun addDog(name: String, owner: String) {
        viewModelScope.launch {
            repository.addDog(name, owner)
        }
    }

    fun updateDog(dog: Dog) {
        viewModelScope.launch {
            repository.updateDog(dog)
        }
    }

    fun deleteDog(dog: Dog) {
        viewModelScope.launch {
            repository.deleteDog(dog)
        }
    }
}
