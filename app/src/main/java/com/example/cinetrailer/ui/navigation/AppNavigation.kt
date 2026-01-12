package com.example.cinetrailer.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cinetrailer.data.Movie
import com.example.cinetrailer.data.movie.models.MovieViewModel
import com.example.cinetrailer.ui.screens.DetailsScreen
import com.example.cinetrailer.ui.screens.FavoritesScreen
import com.example.cinetrailer.ui.screens.HomeScreen
import com.example.cinetrailer.ui.screens.SearchScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val movieViewModel: MovieViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = AppScreens.Home.route) {
            HomeScreen(
                viewModel = movieViewModel,
                onMovieClick = { movie: Movie ->
                    navController.navigate("details/${movie.id}")
                }
            )
        }

        composable(route = AppScreens.Search.route) {
            SearchScreen(
                viewModel = movieViewModel,
                onMovieClick = { movie: Movie ->
                    navController.navigate("details/${movie.id}")
                }
            )
        }

        composable(route = AppScreens.Favorites.route) {
            FavoritesScreen(viewModel = movieViewModel)
        }

        composable(
            route = "details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val movie = movieViewModel.getMovieById(movieId)

            if (movie != null) {
                DetailsScreen(
                    movie = movie,
                    viewModel = movieViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}