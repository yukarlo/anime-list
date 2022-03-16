package com.yukarlo.anime.navigation.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yukarlo.anime.MainScreen
import com.yukarlo.anime.feature.anime.details.AnimeDetailsScreen
import com.yukarlo.anime.feature.anime.list.AnimeListScreen

@Composable
internal fun MainScreenNavigationConfig(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.AnimeMain.route
    ) {
        composable(route = NavigationScreens.AnimeMain.route) {
            MainScreen(
                viewAll = {
                    val bundle: Bundle = Bundle().apply {
                        putParcelable(
                            NavigationScreens.ViewAllAnime.key,
                            it
                        )
                    }

                    navController.navigateTo(
                        route = NavigationScreens.ViewAllAnime.route,
                        args = bundle
                    )
                },
                navigateToDetails = { animeId ->
                    navController.navigate(route = "${NavigationScreens.AnimeDetails.route}/$animeId")
                }
            )
        }
        composable(
            route = NavigationScreens.ViewAllAnime.route
        ) {
            AnimeListScreen(
                navigateUp = navController::navigateUp,
                navigateToDetails = { animeId ->
                    navController.navigate(route = "${NavigationScreens.AnimeDetails.route}/$animeId")
                }
            )
        }
        composable(
            route = "${NavigationScreens.AnimeDetails.route}/{${NavigationScreens.AnimeDetails.key}}",
            arguments = listOf(navArgument(NavigationScreens.AnimeDetails.key) {
                type = NavType.IntType
            })
        ) {
            AnimeDetailsScreen(
                navigateUp = navController::navigateUp,
                openDetails = { animeId ->
                    navController.navigate(route = "${NavigationScreens.AnimeDetails.route}/$animeId")
                }
            )
        }
    }
}

private fun NavController.navigateTo(
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
