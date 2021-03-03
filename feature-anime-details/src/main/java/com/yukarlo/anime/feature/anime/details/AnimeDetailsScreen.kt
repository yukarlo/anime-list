package com.yukarlo.anime.feature.anime.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val factory = HiltViewModelFactory(
        context = LocalContext.current,
        navBackStackEntry = navBackStackEntry
    )
    val viewModel: AnimeDetailsViewModel = viewModel(
        key = AnimeDetailsViewModel::class.java.simpleName,
        factory = factory
    )
}
