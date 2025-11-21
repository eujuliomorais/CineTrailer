package com.example.cinetrailer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

// Rotas
sealed class AppScreens(val route: String, val title: String, val icon: ImageVector) {
    object Home : AppScreens("home", "Início", Icons.Default.Home)
    object Search : AppScreens("search", "Descobrir", Icons.Default.Search)
    object Favorites : AppScreens("favorites", "Favoritos", Icons.Default.Favorite)
}