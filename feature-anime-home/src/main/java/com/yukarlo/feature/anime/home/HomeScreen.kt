package com.yukarlo.feature.anime.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.AnimeWithTextOverlay
import com.yukarlo.anime.common.android.components.LazyGrid
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Image
import com.yukarlo.anime.core.model.Title

@Composable
fun HomeScreen(
    navBackStackEntry: NavBackStackEntry
) {
    val factory = HiltViewModelFactory(
        context = LocalContext.current,
        navBackStackEntry = navBackStackEntry
    )
    val viewModel: HomeViewModel = viewModel("HomeViewModel", factory)

    viewModel.onUpdateHome.collectAsState().value.let { homeState ->
        ScreenState(
            result = homeState.result,
            retry = {
                viewModel.retry()
            },
            renderView = {
                AnimeList(
                    animeList = homeState.homeItems,
                    animeBanner = homeState.homeAnimeBanner,
                    requestNextPage = viewModel::requestNextPage,
                    dispose = { viewModel }
                )
            }
        )
    }
}

@Composable
private fun AnimeList(
    animeList: List<Anime>,
    animeBanner: Anime?,
    requestNextPage: () -> Unit,
    dispose: () -> Unit
) {
    Column {
        LazyGrid(
            items = animeList,
            rows = 3,
            headerText = "ALL TIME TOP ANIME",
            viewAll = {
            },
            itemContent = { it: Anime, index: Int ->
                DisposableEffect(Unit) {
                    if (index == animeList.lastIndex) {
                        requestNextPage()
                    }
                    onDispose {
                        dispose()
                    }
                }
                AnimeCard(anime = it)
            },
            banner = {
                animeBanner?.let {
                    AnimeWithTextOverlay(anime = it)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val animeList = listOf(
        Anime(
            title = Title("Anime 1"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing"
        ),
        Anime(
            title = Title("Anime 2"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing"
        ),
        Anime(
            title = Title("Anime 3"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing"
        )
    )
    AnimeList(
        animeList = animeList,
        animeBanner = Anime(
            title = Title("Anime 0"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing"
        ),
        requestNextPage = { },
        dispose = { }
    )
}
