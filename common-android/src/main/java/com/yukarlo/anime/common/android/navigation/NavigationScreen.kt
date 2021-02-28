package com.yukarlo.anime.common.android.navigation

sealed class NavigationScreens(
    val route: String,
    val label: String,
) {
    object AnimeDetails : NavigationScreens(
        route = "AnimeDetails",
        label = "Anime Details"
    )

    object ViewAllAnime : NavigationScreens(
        route = "ViewAllAnime",
        label = "All Anime"
    )
}