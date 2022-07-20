package com.yukarlo.feature.anime.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yukarlo.anime.common.android.components.*
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewAll: (AnimeInputModel) -> Unit,
    navigateToDetails: (Int?) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        scope.launch {
            viewModel.onNavigate
                .collect {
                    viewAll(it)
                }
        }
        onDispose {
            viewModel
        }
    }

    val homeState by viewModel.onUpdateHome.collectAsState()
    ScreenState(result = homeState.result, retry = viewModel::retry) {
        AnimeList(
            homeState.trendingAnime,
            homeState.popularThisSeasonAnime,
            homeState.popularThisSeasonAnime,
            homeState.topTenAnime,
            onAnimeClick = navigateToDetails,
            viewAll = { viewModel.navigateTo(it) }
        )
    }
}

@Composable
private fun AnimeList(
    trending: List<Anime>,
    allTimePopular: List<Anime>,
    popularThisSeason: List<Anime>,
    topTen: List<Anime>,
    onAnimeClick: (Int?) -> Unit,
    viewAll: (AnimeSortTypes) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        Box {
            if (topTen.isNotEmpty()) {
                AnimeWithTextOverlay(
                    anime = topTen.random(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
            ToolBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
            ) { }
        }

        if (trending.isNotEmpty()) {
            AnimeRowCategory(AnimeSortTypes.TrendingAnime.title, trending, onAnimeClick) {
                viewAll(AnimeSortTypes.TrendingAnime)
            }

        }

        if (allTimePopular.isNotEmpty()) {
            AnimeRowCategory(AnimeSortTypes.AllTimePopular.title, allTimePopular, onAnimeClick) {
                viewAll(AnimeSortTypes.AllTimePopular)
            }

        }

        if (popularThisSeason.isNotEmpty()) {
            AnimeRowCategory(
                AnimeSortTypes.PopularThisSeason.title,
                popularThisSeason,
                onAnimeClick
            ) {
                viewAll(AnimeSortTypes.PopularThisSeason)
            }

        }

        if (topTen.isNotEmpty()) {
            AnimeRowCategory(AnimeSortTypes.Top10.title, topTen, onAnimeClick) {
                viewAll(AnimeSortTypes.Top10)
            }
        }

        Spacer(modifier = Modifier.padding(top = 64.dp))
    }
}

@Composable
private fun AnimeRowCategory(
    title: String,
    anime: List<Anime>,
    onAnimeClick: (Int?) -> Unit,
    viewAll: () -> Unit
) {
    ListHeaderTitle(
        title = title,
        viewAll = viewAll
    )

    HorizontalList(
        items = anime
    ) { item: Anime, modifier: Modifier ->
        AnimeCard(
            anime = item,
            modifier = modifier
                .width(width = 140.dp)
                .height(height = 260.dp),
            onClick = onAnimeClick
        )
    }

    Spacer(modifier = Modifier.padding(top = 16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val animeList = listOf(
        Anime(
            id = 1,
            title = Title("Anime 1"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing",
            startDate = Date(month = "1", day = "2", year = "2019"),
            endDate = Date(month = "1", day = "2", year = "2021"),
            genres = "",
            format = "tv"
        ),
        Anime(
            id = 2,
            title = Title("Anime 2"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing",
            startDate = Date(month = "1", day = "2", year = "2019"),
            endDate = Date(month = "1", day = "2", year = "2021"),
            genres = "",
            format = "tv"
        ),
        Anime(
            id = 3,
            title = Title("Anime 3"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing",
            startDate = Date(month = "1", day = "2", year = "2019"),
            endDate = Date(month = "1", day = "2", year = "2021"),
            genres = "",
            format = "tv"
        )
    )

    AnimeList(
        trending = animeList,
        popularThisSeason = animeList,
        allTimePopular = animeList,
        topTen = animeList,
        viewAll = { },
        onAnimeClick = { },
    )
}
