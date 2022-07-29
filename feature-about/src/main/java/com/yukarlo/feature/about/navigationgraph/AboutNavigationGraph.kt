package com.yukarlo.feature.about.navigationgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yukarlo.anime.feature.about.api.AboutNavigation
import com.yukarlo.feature.about.AccountScreen

internal fun NavGraphBuilder.aboutGraph(navController: NavHostController) {
    composable(route = AboutNavigation.createRoute()) {
        AccountScreen(navController = navController)
    }
}
