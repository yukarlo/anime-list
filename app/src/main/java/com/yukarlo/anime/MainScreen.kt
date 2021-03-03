package com.yukarlo.anime

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.yukarlo.anime.navigation.bottom.BottomNavigationScreens
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.common.android.navigation.BackHandler
import com.yukarlo.anime.common.android.ui.theme.AnimeTheme
import com.yukarlo.feature.anime.home.HomeScreen
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
internal fun MainScreen(
    viewAll: (AnimeInputModel) -> Unit,
    navBackStackEntry: NavBackStackEntry,
    navigateToDetails: (Int?) -> Unit
) {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.About
    )

    val (selectedScreen, setSelectedScreen) = remember { mutableStateOf(bottomNavigationItems.first()) }

    AnimeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.navigationBarsHeight(additional = 56.dp)
                ) {
                    bottomNavigationItems.forEach { screen ->
                        BottomNavigationItem(
                            modifier = Modifier.navigationBarsPadding(),
                            icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                            label = { Text(text = screen.label) },
                            selected = screen == selectedScreen,
                            alwaysShowLabel = false,
                            onClick = { setSelectedScreen(screen) }
                        )
                    }
                }
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
                    },
                    navBackStackEntry = navBackStackEntry
                )
                BottomNavigationScreens.Search -> SearchScreen(
                    navBackStackEntry = navBackStackEntry,
                    onBack = {
                        setSelectedScreen(BottomNavigationScreens.Home)
                    }
                )
                BottomNavigationScreens.About -> AccountScreen(
                    navBackStackEntry = navBackStackEntry,
                    onBack = {
                        setSelectedScreen(BottomNavigationScreens.Home)
                    })
            }
        }
    }
}

@Composable
fun SearchScreen(navBackStackEntry: NavBackStackEntry, onBack: () -> Unit) {
    Text(text = "Search")

    BackHandler(onBack = {
        onBack()
    })
}

@Composable
fun AccountScreen(navBackStackEntry: NavBackStackEntry, onBack: () -> Unit) {
    Text(text = "Account")

    BackHandler(onBack = {
        onBack()
    })
}
