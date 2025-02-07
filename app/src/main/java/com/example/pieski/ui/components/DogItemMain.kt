package com.example.pieski.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pieski.data.Dog
import coil.compose.AsyncImage

@Composable
fun DogItemMain(
    dog: Dog,
    onFavoriteClick: () -> Unit,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        AsyncImage(
            model = dog.imageUrl,
            contentDescription = "Zdjęcie psa",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = dog.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = dog.owner, style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(onClick = onFavoriteClick) {
            if (dog.isFavorite) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Usuń z ulubionych",
                    tint = androidx.compose.ui.graphics.Color.Red
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Dodaj do ulubionych"
                )
            }
        }
    }
}
