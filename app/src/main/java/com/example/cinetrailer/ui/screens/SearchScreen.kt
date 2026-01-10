package com.example.cinetrailer.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.RetrofitInstance
import com.example.cinetrailer.ui.components.MovieCard
import kotlinx.coroutines.launch

@Composable
fun SearchScreen() {
    var query by remember { mutableStateOf("") }

    var nowPlayingMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var popularMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var upcomingMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }

    var searchResults by remember { mutableStateOf<List<Movie>>(emptyList()) }

    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        try {
            val nowPlaying = RetrofitInstance.api.getNowPlayingMovies()
            val popular = RetrofitInstance.api.getPopularMovies()
            val upcoming = RetrofitInstance.api.getUpcomingMovies()

            nowPlayingMovies = nowPlaying.results
            popularMovies = popular.results
            upcomingMovies = upcoming.results

        } catch (e: Exception) {
            Log.e("SearchScreen", "Erro ao carregar categorias: ${e.message}")
        }
    }

    fun performSearch() {
        if (query.isNotEmpty()) {
            scope.launch {
                try {
                    val response = RetrofitInstance.api.searchMovies(query)
                    searchResults = response.results
                    focusManager.clearFocus()
                } catch (e: Exception) {
                    Log.e("SearchScreen", "Erro na busca: ${e.message}")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = query,
            onValueChange = {
                query = it
                if (it.isEmpty()) searchResults = emptyList()
            },
            placeholder = { Text("O que você procura?") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = CircleShape,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { performSearch() }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (query.isEmpty() || searchResults.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                SectionTitle("Em cartaz")
                HorizontalMovieLoop(nowPlayingMovies)

                SectionTitle("Populares")
                HorizontalMovieLoop(popularMovies)

                SectionTitle("Em breve")
                HorizontalMovieLoop(upcomingMovies)

                Spacer(modifier = Modifier.height(100.dp))
            }

        } else {
            Text(
                text = "Resultados para \"$query\"",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(searchResults) { movie ->
                    MovieCard(
                        posterPath = movie.poster_path,
                        onClick = { println("Filme: ${movie.title}") }
                    )
                }
            }
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp)
    )
}

@Composable
fun HorizontalMovieLoop(movies: List<Movie>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                posterPath = movie.poster_path,
                onClick = { println("Filme: ${movie.title}") },
                modifier = Modifier.width(135.dp)
            )
        }
    }
}