package com.yukarlo.anime.navigation.bottom

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.yukarlo.anime.core.navigation.BottomNavigationScreen
import com.yukarlo.feature.about.AboutNavigation
import com.yukarlo.feature.anime.home.HomeNavigation
import com.yukarlo.feature.anime.search.SearchNavigation

@Composable
internal fun AppBottomNavigation(
    currentSelectedScreen: BottomNavigationScreen,
    newScreen: (BottomNavigationScreen) -> Unit
) {
    val bottomNavigationItems = listOf(
        HomeNavigation,
        SearchNavigation,
        AboutNavigation
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
