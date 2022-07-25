package com.yukarlo.feature.anime.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.yukarlo.anime.core.navigation.BottomNavigationScreen

object SearchNavigation : BottomNavigationScreen {
    override val route = "Search"
    override val label = "Search"
    override val icon = Icons.Filled.Search
}
