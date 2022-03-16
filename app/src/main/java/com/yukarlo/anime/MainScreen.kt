package com.yukarlo.anime

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.navigation.bottom.AppBottomNavigation
import com.yukarlo.anime.navigation.bottom.BottomNavigationScreens
import com.yukarlo.feature.about.AccountScreen
import com.yukarlo.feature.anime.home.HomeScreen
import com.yukarlo.feature.anime.search.SearchScreen

@Composable
internal fun MainScreen(
    viewAll: (AnimeInputModel) -> Unit,
    navigateToDetails: (Int?) -> Unit
) {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.About
    )

    val (selectedScreen, setSelectedScreen) = remember { mutableStateOf(bottomNavigationItems.first()) }

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                currentSelectedScreen = selectedScreen,
                newScreen = {
                    setSelectedScreen(it)
                }
            )
        },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (selectedScreen) {
            BottomNavigationScreens.Home -> HomeScreen(
                viewAll = {
                    viewAll(it)
                },
                navigateToDetails = {
                    navigateToDetails(it)
                }
            )
            BottomNavigationScreens.Search -> SearchScreen(
                onBack = {
                    setSelectedScreen(BottomNavigationScreens.Home)
                }
            )
            BottomNavigationScreens.About -> AccountScreen(
                onBack = {
                    setSelectedScreen(BottomNavigationScreens.Home)
                })
        }
    }
}
