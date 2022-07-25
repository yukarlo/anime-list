package com.yukarlo.anime.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector

interface BottomNavigationScreen {
    val route: String
    val label: String
    val icon: ImageVector
}
