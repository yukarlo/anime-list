package com.yukarlo.anime.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yukarlo.anime.BottomNavigationScreens
import com.yukarlo.anime.common.android.navigation.NavigationScreens
import com.yukarlo.anime.feature.anime.list.AnimeListScreen
import com.yukarlo.feature.anime.home.HomeScreen

@Composable
internal fun MainScreenNavigationConfig(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(route = BottomNavigationScreens.Home.route) { navBackStackEntry ->
            HomeScreen(
                navBackStackEntry = navBackStackEntry,
                navController = navController
            )
        }
        composable(route = BottomNavigationScreens.Search.route) {}
        composable(route = BottomNavigationScreens.About.route) {}
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