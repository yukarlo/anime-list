package com.yukarlo.feature.anime.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.yukarlo.anime.core.navigation.BottomNavigationScreen

object HomeNavigation : BottomNavigationScreen {
    override val route = "Home"
    override val label = "Home"
    override val icon = Icons.Filled.Home
}
