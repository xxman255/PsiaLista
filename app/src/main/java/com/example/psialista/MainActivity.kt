package com.example.psialista

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Delete
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psialista.ui.theme.PsyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PsyTheme {
                DogListScreen()
            }
        }
    }
}

@Composable
fun DogListScreen() {
    var nazwaPsa by remember { mutableStateOf(TextFieldValue("")) }
    var listaPieskow by remember { mutableStateOf(listOf<String>()) }
    var ulubionePieski by remember { mutableStateOf(setOf<String>()) }
    var wiadomoscBledu by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "")

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = nazwaPsa,
                onValueChange = {
                    nazwaPsa = it
                    wiadomoscBledu = null
                },
                label = { Text("Poszukaj lub dodaj pieska 🐕") },
                modifier = Modifier
                    .weight(1f)
                    .background(if (wiadomoscBledu != null) Color(0xFFFFCDD2) else Color.Transparent),
                singleLine = true,
                isError = wiadomoscBledu != null
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    val przycietaNazwa = nazwaPsa.text.trim()
                    if (przycietaNazwa.isNotBlank()) {
                        if (!listaPieskow.contains(przycietaNazwa)) {
                            listaPieskow = listOf(przycietaNazwa) + listaPieskow
                            nazwaPsa = TextFieldValue("")
                        } else {
                            wiadomoscBledu = "Piesek juz istnieje na liscie!"
                        }
                    }
                },
                enabled = nazwaPsa.text.isNotBlank()
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Dodaj pieska")
            }

            IconButton(
                onClick = {},
                enabled = nazwaPsa.text.isNotBlank()
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Szukaj pieska")
            }
        }

        wiadomoscBledu?.let {
            Text(text = it, color = Color.Red, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "🐶: ${listaPieskow.size}", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "💜: ${ulubionePieski.size}", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(listaPieskow) { piesek ->
                DogItem(
                    name = piesek,
                    isFavorite = ulubionePieski.contains(piesek),
                    onFavoriteClick = {
                        ulubionePieski = if (ulubionePieski.contains(piesek)) {
                            ulubionePieski - piesek
                        } else {
                            ulubionePieski + piesek
                        }
                        listaPieskow = listaPieskow.sortedByDescending { ulubionePieski.contains(it) }
                    },
                    onDeleteClick = {
                        listaPieskow = listaPieskow - piesek
                        ulubionePieski = ulubionePieski - piesek
                    }
                )
            }
        }
    }
}

@Composable
fun DogItem(name: String, isFavorite: Boolean, onFavoriteClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, fontSize = 18.sp, modifier = Modifier.weight(1f))

            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Ulubiony",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }

            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Usun",
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogListScreenPreview() {
    PsyTheme {
        DogListScreen()
    }
}
