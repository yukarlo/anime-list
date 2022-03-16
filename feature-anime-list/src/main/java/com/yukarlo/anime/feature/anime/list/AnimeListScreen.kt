package com.yukarlo.anime.feature.anime.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.accompanist.insets.statusBarsPadding
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.components.ToolBar
import com.yukarlo.anime.common.android.components.VerticalGrid
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.Anime

@Composable
fun AnimeListScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
    parcelable: AnimeInputModel?,
    navigateToDetails: (Int?) -> Unit,
) {
    val parentEntry = remember { navController.getBackStackEntry(navBackStackEntry.destination.route ?: "") }
    val viewModel: AnimeListViewModel = hiltViewModel(parentEntry)

    LaunchedEffect(parcelable) {
        viewModel.fetchAnime(inputModel = parcelable)
    }

    val animeScreenState by viewModel.onUpdateAnimeList.collectAsState()
    ScreenState(
        result = animeScreenState.result,
        retry = {
            viewModel.retry(inputModel = parcelable)
        }
    ) {
        Scaffold(
            modifier = Modifier.statusBarsPadding(),
            topBar = {
                if (animeScreenState.toolbarTitle.isNotBlank()) {
                    ToolBar(
                        title = animeScreenState.toolbarTitle,
                        onUp = { navController.navigateUp() }
                    )
                }
            }
        ) {
            AnimeList(
                animeList = animeScreenState.animeList,
                onAnimeClick = navigateToDetails
            ) { isOnLastIndex ->
                if (isOnLastIndex) {
                    viewModel.fetchNewPage(inputModel = parcelable)
                }
            }
            Spacer(modifier = Modifier.padding(top = 64.dp))
        }
    }
}

@Composable
private fun AnimeList(
    animeList: List<Anime>,
    onAnimeClick: (Int?) -> Unit,
    hasReachedLastIndex: @Composable (Boolean) -> Unit
) {
    VerticalGrid(
        items = animeList,
    ) { item: Anime, index: Int ->
        hasReachedLastIndex(index == animeList.lastIndex)

        AnimeCard(
            anime = item,
            onClick = { animeId ->
                animeId?.let {
                    onAnimeClick(it)
                }
            },
            modifier = Modifier
                .width(width = 140.dp)
                .height(height = 260.dp)
        )
    }
}
