package com.yukarlo.anime.navigation.toplevel

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yukarlo.anime.MainScreen
import com.yukarlo.anime.core.navigation.NavigationScreen
import com.yukarlo.anime.feature.anime.details.AnimeDetailsNavigation
import com.yukarlo.anime.feature.anime.list.AnimeListNavigation
import com.yukarlo.anime.feature.anime.list.navigateToList

object MainScreenNavigation : NavigationScreen {
    override val key = ""
    override val route = "AnimeMain"
    override val label = "Anime Main"
}

fun NavGraphBuilder.mainScreenGraph(navController: NavController) {
    composable(route = MainScreenNavigation.createRoute()) {
        MainScreen(
            viewAll = {
                val bundle: Bundle =
                    Bundle().apply { putParcelable(AnimeListNavigation.key, it) }

                navController.navigateToList(
                    route = AnimeListNavigation.createRoute(),
                    args = bundle
                )
            },
            navigateToDetails = { animeId ->
                navController.navigate(route = AnimeDetailsNavigation.createRouteWithId(animeId))
            }
        )
    }
}
