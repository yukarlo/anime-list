package com.yukarlo.anime.navigation.bottom

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
internal fun AppBottomNavigation(
    navController: NavHostController,
    selectScreen: (String)-> Unit
) {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.About
    )

    BottomNavigation(
        modifier = Modifier.navigationBarsHeight(additional = 56.dp)
    ) {
        val currentRoute = currentRoute(navController = navController)
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.navigationBarsPadding(),
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                label = { Text(text = screen.label) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false,
                onClick = {
                    if (currentRoute != screen.route) {
                        selectScreen(screen.route)
//                        navController.navigate(screen.route) {
//                            popUpTo = navController.graph.startDestination
//                            launchSingleTop = true
//                        }
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