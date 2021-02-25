package com.yukarlo.anime

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.yukarlo.anime.BottomNavigationScreens.*
import com.yukarlo.anime.components.AppBottomNavigation
import com.yukarlo.anime.components.MainScreenNavigationConfig

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        Home,
        Search,
        About
    )

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                navController = navController,
                items = bottomNavigationItems
            )
        },
    ) {
        MainScreenNavigationConfig(navController = navController)
    }
}
