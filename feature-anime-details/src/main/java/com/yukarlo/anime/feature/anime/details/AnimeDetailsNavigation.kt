package com.yukarlo.anime.feature.anime.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yukarlo.anime.core.navigation.NavigationScreen

object AnimeDetailsNavigation : NavigationScreen {
    override val key = "AnimeIdKey"
    override val route = "AnimeDetails"
    override val label = "Anime Details"
}

fun NavGraphBuilder.animeDetailsGraph(navController: NavController) {
    composable(
        route = AnimeDetailsNavigation.createRoute(),
        arguments = listOf(navArgument(AnimeDetailsNavigation.key) {
            type = NavType.IntType
        })
    ) {
        AnimeDetailsScreen(
            navigateUp = navController::navigateUp,
            openDetails = { animeId ->
                navController.navigate(route = AnimeDetailsNavigation.createRouteWithId(animeId))
            }
        )
    }
}
