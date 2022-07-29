package com.yukarlo.feature.anime.home.naviationgraph

import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yukarlo.anime.feature.list.api.AnimeListNavigation
import com.yukarlo.anime.feature.list.api.navigateToList
import com.yukarlo.anime.feature.details.api.AnimeDetailsNavigation
import com.yukarlo.anime.feature.home.api.HomeNavigation
import com.yukarlo.feature.anime.home.HomeScreen
import com.yukarlo.feature.anime.home.HomeViewModel

internal fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(route = HomeNavigation.createRoute()) {
        val viewModel: HomeViewModel = hiltViewModel()
        HomeScreen(
            viewAll = {
                val bundle: Bundle =
                    Bundle().apply { putParcelable(AnimeListNavigation.key, it) }

                navController.navigateToList(
                    route = AnimeListNavigation.createRoute(),
                    args = bundle
                )
            },
            navigateToDetails = {
                navController.navigate(route = AnimeDetailsNavigation.createRouteWithId(it))
            },
            viewModel = viewModel
        )
    }
}
