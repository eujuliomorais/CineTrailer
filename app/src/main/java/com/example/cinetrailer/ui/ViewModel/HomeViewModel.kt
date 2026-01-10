package com.example.cinetrailer.ui.ViewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.RetrofitInstance
import com.example.cinetrailer.data.local.AppDatabase
import com.example.cinetrailer.data.local.MovieEntity
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao = AppDatabase.getDatabase(application).movieDao()
    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Loading)
        private set

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        uiState = HomeUiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies("192cbca5e86b88b51a3902158c2a0d77")
                uiState = HomeUiState.Success(response.results)
            } catch (e: Exception) {
                uiState = HomeUiState.Error("Erro de conexão ou chave inválida")
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            movieDao.insertFavorite(
                MovieEntity(movie.id, movie.title, movie.poster_path)
            )
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val movies: List<Movie>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}