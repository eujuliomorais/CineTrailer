package com.example.cinetrailer.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinetrailer.ui.screens.FavoritesScreen
import com.example.cinetrailer.ui.screens.HomeScreen
import com.example.cinetrailer.ui.screens.SearchScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = AppScreens.Home.route) {
            HomeScreen()
        }
        composable(route = AppScreens.Search.route) {
            SearchScreen()
        }
        composable(route = AppScreens.Favorites.route) {
            FavoritesScreen()
        }
    }
}