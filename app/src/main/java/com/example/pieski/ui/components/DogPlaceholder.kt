package com.example.pieski.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DogPlaceholder(modifier: Modifier = Modifier.size(64.dp)) {
    Box(
        modifier = modifier
            .background(Color.LightGray, shape = MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "🐶")
    }
}
