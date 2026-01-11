package com.example.cinetrailer.data.movie.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.movie.database.AppDatabase
import com.example.cinetrailer.data.movie.entity.MovieEntity
import kotlinx.coroutines.launch
// MovieViewModel.kt
class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao = AppDatabase.getDatabase(application).movieDao()
    private var _currentMovies = mutableListOf<Movie>()

    fun updateCurrentMovies(newList: List<Movie>) {
        _currentMovies.clear()
        _currentMovies.addAll(newList)
    }

    fun getMovieById(id: Int?): Movie? {
        return _currentMovies.find { it.id == id }
    }

    val favoriteMovies = movieDao.getAllFavorites()

    fun saveFavorite(movie: Movie, rating: Float = 0f) {
        viewModelScope.launch {
            movieDao.insertFavorite(
                MovieEntity(
                    id = movie.id,
                    title = movie.title,
                    posterPath = movie.poster_path ?: "",
                    rating = rating
                )
            )
        }
    }

    fun updateRating(movie: MovieEntity, newRating: Float) {
        viewModelScope.launch {
            val updatedMovie = movie.copy(rating = newRating)
            movieDao.insertFavorite(updatedMovie)
        }
    }

    fun removeFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            movieDao.deleteFavorite(movie)
        }
    }
}