package com.example.cinetrailer.ui.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.local.AppDatabase
import com.example.cinetrailer.data.local.MovieEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao = AppDatabase.getDatabase(application).movieDao()

    val favorites: StateFlow<List<Movie>> = movieDao.getAllFavorites()
        .map { entities ->
            entities.map { Movie(it.id, it.title, it.poster_path) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavorite(movie: Movie) {
        viewModelScope.launch {
            movieDao.deleteFavorite(MovieEntity(movie.id, movie.title, movie.poster_path))
        }
    }
}