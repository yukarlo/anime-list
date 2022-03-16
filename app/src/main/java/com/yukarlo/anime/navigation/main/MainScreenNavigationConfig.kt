package com.yukarlo.anime.navigation.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(route = NavigationScreens.AnimeMain.route) { navBackStackEntry ->
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
                },
                navBackStackEntry = navBackStackEntry
            )
        }
        composable(
            route = NavigationScreens.ViewAllAnime.route
        ) { navBackStackEntry ->
            AnimeListScreen(
                navBackStackEntry = navBackStackEntry,
                navController = navController,
                parcelable = navBackStackEntry.arguments?.getParcelable(NavigationScreens.ViewAllAnime.key),
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
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            AnimeDetailsScreen(
                animeId = arguments.getInt(NavigationScreens.AnimeDetails.key),
                navBackStackEntry = navBackStackEntry,
                navController = navController
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
