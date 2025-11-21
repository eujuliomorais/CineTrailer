package com.example.cinetrailer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinetrailer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CineTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo CineTrailer",
                modifier = Modifier.height(40.dp)
            )
        },
        actions = {
            IconButton(onClick = { /* Colocar ação do botão de mais opções */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Mais opções",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        modifier = modifier
    )
}