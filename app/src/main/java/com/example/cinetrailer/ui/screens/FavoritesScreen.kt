package com.example.cinetrailer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinetrailer.data.movie.models.MovieViewModel
import com.example.cinetrailer.ui.components.RatingBar

@Composable
fun FavoritesScreen(viewModel: MovieViewModel = viewModel()) {

    val favorites by viewModel.favoriteMovies.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF121212))) {

        Text("Meus Favoritos",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(favorites) { movie ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text(movie.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold)

                        RatingBar(
                            rating = movie.rating,
                            onRatingChange = { newRating ->
                                viewModel.updateRating(movie, newRating)
                            }
                        )
                    }

                    IconButton(onClick = { viewModel.removeFavorite(movie) }) {
                        Icon(Icons.Default.Delete,
                            contentDescription = "Excluir",
                            tint = Color.Red)
                    }
                }
            }
        }
    }
}
