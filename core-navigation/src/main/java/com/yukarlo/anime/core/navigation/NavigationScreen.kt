package com.yukarlo.anime.core.navigation

interface NavigationScreen {
    val key: String
    val route: String
    val label: String
    fun createRoute(): String = "${route}/{${key}}"
    fun createRouteWithId(id: Int?): String = "$route/$id"
}
