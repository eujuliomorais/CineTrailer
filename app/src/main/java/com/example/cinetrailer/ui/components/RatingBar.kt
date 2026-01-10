package com.example.cinetrailer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    onRatingChange: (Float) -> Unit
) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            val starIndex = index + 1
            val isSelected = starIndex <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star
            val color = if (isSelected) Color(0xFFFFD700) else Color.Gray

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRatingChange(starIndex.toFloat()) }
            )
        }
    }
}