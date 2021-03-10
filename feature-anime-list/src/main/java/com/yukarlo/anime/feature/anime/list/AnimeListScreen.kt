package com.yukarlo.anime.feature.anime.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.components.ToolBar
import com.yukarlo.anime.common.android.components.VerticalGrid
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.Anime
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun AnimeListScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
    parcelable: AnimeInputModel?,
    navigateToDetails: (Int?) -> Unit,
) {
    val factory = HiltViewModelFactory(
        context = LocalContext.current,
        navBackStackEntry = navBackStackEntry
    )
    val viewModel: AnimeListViewModel = viewModel(
        key = AnimeListViewModel::class.java.simpleName,
        factory = factory
    )

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
                requestNextPage = {
                    viewModel.fetchNewPage(inputModel = parcelable)
                },
                dispose = { viewModel },
                onAnimeClick = navigateToDetails
            )
            Spacer(modifier = Modifier.padding(top = 64.dp))
        }
    }
}

@Composable
private fun AnimeList(
    animeList: List<Anime>,
    requestNextPage: () -> Unit,
    dispose: () -> Unit,
    onAnimeClick: (Int?) -> Unit
) {
    VerticalGrid(
        items = animeList,
    ) { item: Anime, index: Int ->
        DisposableEffect(Unit) {
            if (index == animeList.lastIndex) {
                requestNextPage()
            }
            onDispose {
                dispose()
            }
        }

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
