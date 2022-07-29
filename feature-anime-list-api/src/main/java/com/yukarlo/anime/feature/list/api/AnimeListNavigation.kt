package com.yukarlo.anime.feature.list.api

import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.*
import com.yukarlo.anime.core.navigation.NavigationScreen

object AnimeListNavigation : NavigationScreen {
    override val key = "AnimeInputModelKey"
    override val route = "ViewAllAnime"
    override val label = "All Anime"
}

fun NavController.navigateToList(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    graph.matchDeepLink(routeLink)?.let {
        navigate(it.destination.id, args, navOptions, navigatorExtras)
    } ?: navigate(route, navOptions, navigatorExtras)
}
