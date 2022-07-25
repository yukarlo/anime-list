package com.yukarlo.anime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yukarlo.anime.feature.anime.details.animeDetailsGraph
import com.yukarlo.anime.feature.anime.list.animeListGraph
import com.yukarlo.anime.navigation.toplevel.MainScreenNavigation
import com.yukarlo.anime.navigation.toplevel.mainScreenGraph

@Composable
internal fun TopLevelNavigationConfig(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenNavigation.createRoute()
    ) {
        mainScreenGraph(navController)
        animeListGraph(navController)
        animeDetailsGraph(navController)
    }
}
