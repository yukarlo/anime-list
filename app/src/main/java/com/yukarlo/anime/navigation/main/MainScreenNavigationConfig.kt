package com.yukarlo.anime.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.yukarlo.anime.MainScreen
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
                    navController.apply {
                        currentBackStackEntry
                            ?.arguments?.putParcelable(
                                NavigationScreens.ViewAllAnime.parcelableKey,
                                it
                            )
                        navigate(route = NavigationScreens.ViewAllAnime.route)
                    }
                },
                navigateToDetails = {

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
                parcelable = navController.previousBackStackEntry
                    ?.arguments?.getParcelable(NavigationScreens.ViewAllAnime.parcelableKey)
            )
        }
    }
}