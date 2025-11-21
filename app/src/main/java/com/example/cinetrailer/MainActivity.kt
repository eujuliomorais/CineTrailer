package com.example.cinetrailer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cinetrailer.ui.components.BottomNavigationBar
import com.example.cinetrailer.ui.components.CineTopBar
import com.example.cinetrailer.ui.navigation.AppNavigation
import com.example.cinetrailer.ui.theme.CineTrailerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineTrailerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CineTrailerApp()
                }
            }
        }
    }
}

@Composable
fun CineTrailerApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { CineTopBar() },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            paddingValues = innerPadding
        )
    }
}