package com.yukarlo.anime.navigation.main

sealed class NavigationScreens(
    val key: String,
    val route: String,
    val label: String,
) {
    fun createRoute(): String = "${route}/{${key}}"

    object AnimeMain : NavigationScreens(
        key = "",
        route = "AnimeMain",
        label = "Anime Main"
    )

    object AnimeDetails : NavigationScreens(
        key = "AnimeIdKey",
        route = "AnimeDetails",
        label = "Anime Details"
    ) {
        fun createRoute(id: Int?): String = "$route/$id"
    }

    object ViewAllAnime : NavigationScreens(
        key = "AnimeInputModelKey",
        route = "ViewAllAnime",
        label = "All Anime"
    )
}
