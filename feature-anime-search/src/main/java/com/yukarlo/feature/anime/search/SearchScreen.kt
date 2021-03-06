package com.yukarlo.feature.anime.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.yukarlo.anime.common.android.navigation.BackHandler
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun SearchScreen(navBackStackEntry: NavBackStackEntry, onBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
    ) {
        Text(text = "Search")
        Spacer(modifier = Modifier.padding(top = 64.dp))
    }

    BackHandler(onBack = {
        onBack()
    })
}
