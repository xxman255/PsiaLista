package com.example.pieski.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pieski.data.Dog
import com.example.pieski.ui.navigation.Screen
import com.example.pieski.viewmodel.DogViewModel

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Main) }
    val dogViewModel: DogViewModel = hiltViewModel()

    when (val screen = currentScreen) {
        is Screen.Main -> {
            MainScreen(
                dogsFlow = dogViewModel.dogs,
                onAddDog = { name, owner ->
                    dogViewModel.addDog(name, owner)
                },
                onToggleFavorite = { dog ->
                    dogViewModel.updateDog(dog.copy(isFavorite = !dog.isFavorite))
                },
                onDeleteDog = { dog ->
                    dogViewModel.deleteDog(dog)
                },
                onDogClick = { dog ->
                    currentScreen = Screen.DogDetail(dog)
                },
                onSettingsClick = { currentScreen = Screen.Settings },
                onProfileClick = { currentScreen = Screen.Profile }
            )
        }
        is Screen.DogDetail -> {
            DogDetailScreen(
                dog = screen.dog,
                onBack = { currentScreen = Screen.Main },
                onDelete = { dog ->
                    dogViewModel.deleteDog(dog)
                    currentScreen = Screen.Main
                }
            )
        }
        is Screen.Settings -> {
            SettingsScreen(onBack = { currentScreen = Screen.Main })
        }
        is Screen.Profile -> {
            ProfileScreen(onBack = { currentScreen = Screen.Main })
        }
    }
}
