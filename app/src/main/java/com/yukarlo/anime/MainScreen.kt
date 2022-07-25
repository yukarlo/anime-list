package com.yukarlo.anime

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.navigation.bottom.AppBottomNavigation
import com.yukarlo.feature.about.AboutNavigation
import com.yukarlo.feature.about.AccountScreen
import com.yukarlo.feature.anime.home.HomeNavigation
import com.yukarlo.feature.anime.home.HomeScreen
import com.yukarlo.feature.anime.search.SearchNavigation
import com.yukarlo.feature.anime.search.SearchScreen

@Composable
internal fun MainScreen(
    viewAll: (AnimeInputModel) -> Unit,
    navigateToDetails: (Int?) -> Unit
) {
    val bottomNavigationItems = listOf(
        HomeNavigation,
        SearchNavigation,
        AboutNavigation
    )

    val (selectedScreen, setSelectedScreen) = remember { mutableStateOf(bottomNavigationItems.first()) }

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                currentSelectedScreen = selectedScreen,
                newScreen = { setSelectedScreen(it) }
            )
        },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (selectedScreen) {
            HomeNavigation -> HomeScreen(
                viewAll = { viewAll(it) },
                navigateToDetails = { navigateToDetails(it) }
            )
            SearchNavigation -> SearchScreen(onBack = { setSelectedScreen(HomeNavigation) })
            AboutNavigation -> AccountScreen(onBack = { setSelectedScreen(HomeNavigation) })
        }
    }
}
