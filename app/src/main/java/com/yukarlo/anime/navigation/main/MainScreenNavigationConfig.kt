package com.yukarlo.anime.navigation.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
                    navController.apply {
//                        currentBackStackEntry?.arguments = bundle
                        navigate(route = NavigationScreens.ViewAllAnime.route)
                    }
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
                parcelable = navController.previousBackStackEntry
                    ?.arguments?.getParcelable(NavigationScreens.ViewAllAnime.key),
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
