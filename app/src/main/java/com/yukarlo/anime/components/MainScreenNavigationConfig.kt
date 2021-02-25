package com.yukarlo.anime.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yukarlo.anime.BottomNavigationScreens
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
            HomeScreen(navBackStackEntry = navBackStackEntry)
        }
        composable(route = BottomNavigationScreens.Search.route) {}
        composable(route = BottomNavigationScreens.About.route) {}
    }
}