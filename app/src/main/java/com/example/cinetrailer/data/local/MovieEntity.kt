package com.example.cinetrailer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_favorites")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val poster_path: String?
)