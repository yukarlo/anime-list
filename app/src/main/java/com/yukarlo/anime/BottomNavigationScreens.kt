package com.yukarlo.anime

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

internal sealed class BottomNavigationScreens(
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