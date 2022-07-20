package com.yukarlo.anime.navigation.bottom

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

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

    NavigationBar(
        modifier = Modifier.navigationBarsHeight(additional = 72.dp)
    ) {
        bottomNavigationItems.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.navigationBarsPadding(),
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                label = { Text(text = screen.label) },
                selected = currentSelectedScreen == screen,
                onClick = { newScreen(screen) }
            )
        }
    }
}
