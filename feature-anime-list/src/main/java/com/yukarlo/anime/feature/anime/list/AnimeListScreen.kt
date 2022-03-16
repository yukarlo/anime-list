package com.yukarlo.anime.feature.anime.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.statusBarsPadding
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.components.ToolBar
import com.yukarlo.anime.common.android.components.VerticalGrid
import com.yukarlo.anime.core.model.Anime

@Composable
fun AnimeListScreen(
    navigateUp: () -> Unit,
    navigateToDetails: (Int?) -> Unit,
) {
    val viewModel: AnimeListViewModel = hiltViewModel()

    val animeScreenState by viewModel.onUpdateAnimeList.collectAsState()
    ScreenState(
        result = animeScreenState.result,
        retry = { viewModel.retry() }
    ) {
        Scaffold(
            modifier = Modifier.statusBarsPadding(),
            topBar = {
                if (animeScreenState.toolbarTitle.isNotBlank()) {
                    ToolBar(
                        title = animeScreenState.toolbarTitle,
                        onUp = navigateUp
                    )
                }
            }
        ) {
            AnimeList(
                animeList = animeScreenState.animeList,
                onAnimeClick = navigateToDetails
            ) { isOnLastIndex ->
                if (isOnLastIndex) {
                    viewModel.fetchNewPage()
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
