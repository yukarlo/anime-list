package com.yukarlo.anime.feature.about.api

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import com.yukarlo.anime.core.navigation.BottomNavigationScreen

object AboutNavigation : BottomNavigationScreen {
    override val route = "About"
    override val label = "About"
    override val icon = Icons.Filled.AccountBox
}
