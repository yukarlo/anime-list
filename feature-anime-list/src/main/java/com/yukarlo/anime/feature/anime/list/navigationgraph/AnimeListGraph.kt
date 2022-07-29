package com.yukarlo.anime.feature.anime.list.navigationgraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yukarlo.anime.feature.list.api.AnimeListNavigation
import com.yukarlo.anime.feature.anime.list.AnimeListScreen
import com.yukarlo.anime.feature.anime.list.AnimeListViewModel
import com.yukarlo.anime.feature.details.api.AnimeDetailsNavigation

internal fun NavGraphBuilder.animeListGraph(navController: NavHostController) {
    composable(route = AnimeListNavigation.createRoute()) {
        val viewModel: AnimeListViewModel = hiltViewModel()
        AnimeListScreen(viewModel = viewModel, navController = navController, navigateToDetails = {
            navController.navigate(route = AnimeDetailsNavigation.createRouteWithId(it))
        })
    }
}
