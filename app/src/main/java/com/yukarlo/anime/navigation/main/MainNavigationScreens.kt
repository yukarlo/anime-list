package com.yukarlo.anime.navigation.main

sealed class NavigationScreens(
    val parcelableKey: String,
    val route: String,
    val label: String,
) {
    object AnimeMain : NavigationScreens(
        parcelableKey = "",
        route = "AnimeMain",
        label = "Anime Main"
    )

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