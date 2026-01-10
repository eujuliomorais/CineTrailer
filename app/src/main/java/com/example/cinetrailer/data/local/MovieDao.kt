package com.example.cinetrailer.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity)

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity)

    @Query("SELECT * FROM movies_favorites")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movies_favorites WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}