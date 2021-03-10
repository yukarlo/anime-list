package com.yukarlo.anime.navigation.bottom

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
internal fun AppBottomNavigation(
    currentSelectedScreen: BottomNavigationScreens,
    newScreen: (BottomNavigationScreens) -> Unit
) {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.About
    )

    BottomNavigation(
        modifier = Modifier.navigationBarsHeight(additional = 56.dp)
    ) {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.navigationBarsPadding(),
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                label = { Text(text = screen.label) },
                selected = currentSelectedScreen == screen,
                alwaysShowLabel = false,
                onClick = {
                    newScreen(screen)
                }
            )
        }
    }
}
