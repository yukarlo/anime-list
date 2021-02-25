package com.yukarlo.anime

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yukarlo.anime.BottomNavigationScreens.*
import com.yukarlo.feature.anime.home.HomeScreen

sealed class BottomNavigationScreens(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavigationScreens(
        route = "Home",
        label = "Home",
        icon = Icons.Filled.Home
    )

    object Search : BottomNavigationScreens(
        route = "Search",
        label = "Search",
        icon = Icons.Filled.Search
    )

    object About : BottomNavigationScreens(
        route = "About",
        label = "About",
        icon = Icons.Filled.AccountBox
    )
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        Home,
        Search,
        About
    )

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                navController = navController,
                items = bottomNavigationItems
            )
        },
    ) {
        MainScreenNavigationConfigurations(navController = navController)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) { navBackStackEntry ->
            HomeScreen(navBackStackEntry = navBackStackEntry)
        }
        composable(route = Search.route) {}
        composable(route = About.route) {}
    }
}

@Composable
private fun AppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController = navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                label = { Text(text = screen.label) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}
