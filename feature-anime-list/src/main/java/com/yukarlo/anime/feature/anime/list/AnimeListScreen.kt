package com.yukarlo.anime.feature.anime.list

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.components.VerticalGrid
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.Anime

@Composable
fun AnimeListScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
    parcelable: AnimeInputModel?
) {
    val factory = HiltViewModelFactory(
        context = LocalContext.current,
        navBackStackEntry = navBackStackEntry
    )
    val viewModel: AnimeListViewModel = viewModel(
        key = AnimeListViewModel::class.java.simpleName,
        factory = factory
    )

    DisposableEffect(Unit) {
        viewModel.fetchAnime(inputModel = parcelable)
        onDispose {
            viewModel
        }
    }

    viewModel.onUpdateAnimeList.collectAsState().value.let { animeScreenState ->
        ScreenState(
            result = animeScreenState.result,
            retry = {
                viewModel.retry(inputModel = parcelable)
            },
            renderView = {
                Scaffold(
                    topBar = {
                        if (animeScreenState.toolbarTitle.isNotBlank()) {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = animeScreenState.toolbarTitle,
                                    )
                                },
                                elevation = 12.dp
                            )
                        }
                    }
                ) {
                    AnimeList(
                        animeList = animeScreenState.animeList,
                        requestNextPage = {
                            viewModel.fetchNewPage(inputModel = parcelable)
                        },
                        dispose = { viewModel }
                    )
                }
            }
        )
    }
}

@Composable
private fun AnimeList(
    animeList: List<Anime>,
    requestNextPage: () -> Unit,
    dispose: () -> Unit
) {
    VerticalGrid(
        items = animeList,
        requestNextPage = { requestNextPage() },
        dispose = { dispose() }
    )
}
