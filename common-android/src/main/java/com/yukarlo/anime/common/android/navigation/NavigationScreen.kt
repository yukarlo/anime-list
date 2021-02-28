package com.yukarlo.anime.common.android.navigation

sealed class NavigationScreens(
    val parcelableKey: String,
    val route: String,
    val label: String,
) {
    object AnimeDetails : NavigationScreens(
        parcelableKey = "",
        route = "AnimeDetails",
        label = "Anime Details"
    )

    object ViewAllAnime : NavigationScreens(
        parcelableKey = "AnimeInputModelKey",
        route = "ViewAllAnime",
        label = "All Anime"
    )
}