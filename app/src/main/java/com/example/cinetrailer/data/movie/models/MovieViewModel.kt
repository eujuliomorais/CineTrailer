package com.example.cinetrailer.data.movie.models

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.RetrofitInstance
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

    var trailerKey by mutableStateOf<String?>(null)
        private set

    fun loadTrailer(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getMovieVideos(movieId)
                val trailer = response.results.find { it.site == "YouTube" && it.type == "Trailer" }
                trailerKey = trailer?.key
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Erro ao carregar trailer", e)
                trailerKey = null
            }
        }
    }
}