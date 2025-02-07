package com.example.pieski.ui.navigation

import com.example.pieski.data.Dog

sealed class Screen {
    object Main : Screen()
    data class DogDetail(val dog: Dog) : Screen()
    object Settings : Screen()
    object Profile : Screen()
}

