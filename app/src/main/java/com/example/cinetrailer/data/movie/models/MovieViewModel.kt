package com.example.cinetrailer.data.movie.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.movie.database.AppDatabase
import com.example.cinetrailer.data.movie.entity.MovieEntity
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao = AppDatabase.getDatabase(application).movieDao()

    val favoriteMovies = movieDao.getAllFavorites()

    fun addFavorite(movie: Movie) {
        viewModelScope.launch {
            movieDao.insertFavorite(MovieEntity(movie.id,
                movie.title,
                movie.poster_path))
        }
    }

    fun updateRating(movie: MovieEntity, newRating: Float) {
        viewModelScope.launch {
            movieDao.updateFavorite(movie.copy(rating = newRating))
        }
    }

    fun removeFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            movieDao.deleteFavorite(movie)
        }
    }
}