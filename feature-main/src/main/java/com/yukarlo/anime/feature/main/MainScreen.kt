package com.yukarlo.anime.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.yukarlo.anime.core.navigation.ComposeNavigationFactory
import com.yukarlo.anime.core.navigation.addNavigation
import com.yukarlo.anime.feature.about.api.AboutNavigation
import com.yukarlo.anime.feature.home.api.HomeNavigation
import com.yukarlo.anime.feature.search.api.SearchNavigation

@Composable
fun MainScreen(
    navController: NavHostController,
    composeNavigationFactories: @JvmSuppressWildcards Set<ComposeNavigationFactory>
) {
    Scaffold(
        bottomBar = {
            AppBottomNavigation(navController = navController)
        },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(
            navController = navController,
            startDestination = HomeNavigation.createRoute()
        ) {
            composeNavigationFactories.addNavigation(this, navController)
        }
    }
}

@Composable
private fun AppBottomNavigation(
    navController: NavController
) {
    val bottomNavigationItems = listOf(
        HomeNavigation,
        SearchNavigation,
        AboutNavigation
    )

    NavigationBar(
        modifier = Modifier.navigationBarsHeight(additional = 72.dp)
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.navigationBarsPadding(),
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                label = { Text(text = screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
