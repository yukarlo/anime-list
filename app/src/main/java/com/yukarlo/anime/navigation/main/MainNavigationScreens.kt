package com.yukarlo.anime.navigation.main

sealed class NavigationScreens(
    val key: String,
    val route: String,
    val label: String,
) {
    object AnimeMain : NavigationScreens(
        key = "",
        route = "AnimeMain",
        label = "Anime Main"
    )

    object AnimeDetails : NavigationScreens(
        key = "AnimeIdKey",
        route = "AnimeDetails",
        label = "Anime Details"
    )

    object ViewAllAnime : NavigationScreens(
        key = "AnimeInputModelKey",
        route = "ViewAllAnime",
        label = "All Anime"
    )
}