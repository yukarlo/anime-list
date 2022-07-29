package com.yukarlo.anime.feature.anime.details.navigationgraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.yukarlo.anime.feature.anime.details.AnimeDetailsScreen
import com.yukarlo.anime.feature.anime.details.AnimeDetailsViewModel
import com.yukarlo.anime.feature.details.api.AnimeDetailsNavigation

internal fun NavGraphBuilder.animeDetailsGraph(navController: NavHostController) {
    composable(
        route = AnimeDetailsNavigation.createRoute(),
        arguments = listOf(navArgument(AnimeDetailsNavigation.key) {
            type = NavType.IntType
        })
    ) {
        val viewModel: AnimeDetailsViewModel = hiltViewModel()
        AnimeDetailsScreen(
            viewModel = viewModel,
            navController = navController,
            openDetails = {
                navController.navigate(route = AnimeDetailsNavigation.createRouteWithId(it))
            },
        )
    }
}
