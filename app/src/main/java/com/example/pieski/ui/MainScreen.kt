package com.example.pieski.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pieski.data.Dog
import com.example.pieski.ui.components.DogItemMain
import com.example.pieski.ui.components.DogPlaceholder
import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.collectAsState

@Composable
fun MainScreen(
    dogsFlow: Flow<List<Dog>>,
    onAddDog: (name: String, owner: String) -> Unit,
    onToggleFavorite: (Dog) -> Unit,
    onDeleteDog: (Dog) -> Unit,
    onDogClick: (Dog) -> Unit,
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }

    val dogs by dogsFlow.collectAsState(initial = emptyList())

    val filteredDogs = if (searchActive && inputText.isNotBlank()) {
        dogs.filter { it.name.equals(inputText, ignoreCase = true) }
    } else {
        dogs
    }
    val displayedDogs = filteredDogs.sortedByDescending { it.isFavorite }
    val totalCount = dogs.size
    val favoriteCount = dogs.count { it.isFavorite }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), 
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Ustawienia"
                )
            }
            Text(
                text = "Doggos",
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profil"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Wpisz nazwę psa") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { searchActive = inputText.isNotBlank() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Szukaj psa"
                )
            }
            IconButton(onClick = {
                if (inputText.isNotBlank()) {
                    onAddDog(inputText, "Random Owner")
                    inputText = ""
                    searchActive = false
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Dodaj psa"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                DogPlaceholder(modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = totalCount.toString())
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Ulubione",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = favoriteCount.toString())
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(displayedDogs, key = { it.id }) { dog ->
                DogItemMain(
                    dog = dog,
                    onFavoriteClick = { onToggleFavorite(dog) },
                    onItemClick = { onDogClick(dog) }
                )
            }
        }
    }
}
