package com.yukarlo.feature.anime.search.navigationgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yukarlo.anime.feature.search.api.SearchNavigation
import com.yukarlo.feature.anime.search.SearchScreen

internal fun NavGraphBuilder.animeSearchGraph(navController: NavHostController) {
    composable(route = SearchNavigation.createRoute()) {
        SearchScreen(navController = navController)
    }
}
