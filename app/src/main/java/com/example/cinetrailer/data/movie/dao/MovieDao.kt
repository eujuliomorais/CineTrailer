package com.example.cinetrailer.data.movie.dao

import androidx.room.*
import com.example.cinetrailer.data.movie.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity)

    @Update
    suspend fun updateFavorite(movie: MovieEntity)
}