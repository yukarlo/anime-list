package com.yukarlo.feature.anime.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.google.accompanist.insets.statusBarsPadding
import com.yukarlo.anime.common.android.navigation.BackHandler

@Composable
fun SearchScreen(onBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Text(text = "Search")
        Spacer(modifier = Modifier.padding(top = 64.dp))
    }

    BackHandler(onBack = {
        onBack()
    })
}
