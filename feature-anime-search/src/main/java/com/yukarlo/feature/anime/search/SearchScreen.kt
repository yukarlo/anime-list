package com.yukarlo.feature.anime.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun SearchScreen(navController: NavController) {

    BackHandler {
        navController.popBackStack()
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Text(text = "Search")
        Spacer(modifier = Modifier.padding(top = 64.dp))
    }
}
