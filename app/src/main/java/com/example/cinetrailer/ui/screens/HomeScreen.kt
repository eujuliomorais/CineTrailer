package com.example.cinetrailer.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.RetrofitInstance
import com.example.cinetrailer.ui.components.MovieCard
import com.example.cinetrailer.BuildConfig
import com.example.cinetrailer.data.movie.models.MovieViewModel

@Composable
fun HomeScreen(onMovieClick: (Movie) -> Unit, viewModel: MovieViewModel) {
    var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.getPopularMovies()
            movies = response.results
        } catch (e: Exception) {
            Log.e("HomeScreen", "Erro: ${e.message}")
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        SectionTitle("Catálogo")

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies) { movie ->
                MovieCard(
                    posterPath = movie.poster_path,
                    onClick = { onMovieClick(movie) },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}