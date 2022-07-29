package com.yukarlo.anime.feature.anime.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.components.ToolBar
import com.yukarlo.anime.common.android.components.VerticalGrid
import com.yukarlo.anime.core.model.Anime

@Composable
internal fun AnimeListScreen(
    viewModel: AnimeListViewModel,
    navController: NavHostController,
    navigateToDetails: (Int?) -> Unit
) {
    val animeScreenState by viewModel.onUpdateAnimeList.collectAsState()

    BackHandler {
        navController.popBackStack()
    }

    ScreenState(
        result = animeScreenState.result,
        retry = { viewModel.retry() }
    ) {
        Scaffold(
            topBar = {
                if (animeScreenState.toolbarTitle.isNotBlank()) {
                    ToolBar(
                        title = animeScreenState.toolbarTitle,
                        onUp = { navController.popBackStack() }
                    )
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            AnimeList(
                modifier = modifier,
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
    modifier: Modifier,
    animeList: List<Anime>,
    onAnimeClick: (Int?) -> Unit,
    hasReachedLastIndex: @Composable (Boolean) -> Unit
) {
    VerticalGrid(
        modifier = modifier,
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
