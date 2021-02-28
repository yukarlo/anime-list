package com.yukarlo.feature.anime.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.AnimeWithTextOverlay
import com.yukarlo.anime.common.android.components.ListHeaderTitle
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeRequest
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
    val viewModel: HomeViewModel = viewModel(
        key = HomeViewModel::class.java.simpleName,
        factory = factory
    )

    viewModel.onUpdateHome.collectAsState().value.let { homeState ->
        ScreenState(
            result = homeState.result,
            retry = {
                viewModel.retry()
            },
            renderView = {
                AnimeList(
                    homeItems = homeState.homeItems
                )
            }
        )
    }
}

@Composable
private fun AnimeList(
    homeItems: List<HomeItems>
) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        homeItems.forEach { homeItem ->
            val animeList: List<Anime>? = when (homeItem) {
                is HomeItems.TrendingAnime -> {
                    ListHeaderTitle(
                        title = homeItem.request.title,
                        viewAll = { }
                    )
                    homeItem.trendingAnime
                }
                is HomeItems.TopAnime -> {
                    AnimeWithTextOverlay(anime = homeItem.topAnime.orEmpty().random())
                    ListHeaderTitle(
                        title = homeItem.request.title,
                        viewAll = { }
                    )
                    homeItem.topAnime
                }
            }

            LazyRow(
                modifier = Modifier.padding(start = 4.dp, end = 8.dp)
            ) {
                itemsIndexed(items = animeList.orEmpty().take(n = 10)) { _, it ->
                    AnimeCard(anime = it)
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
        }
        Spacer(modifier = Modifier.padding(top = 64.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val animeList = listOf(
        Anime(
            title = Title("Anime 1"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing",
            startDate = "",
            endDate = "",
            genres = "",
            formatAndYear = ""
        ),
        Anime(
            title = Title("Anime 2"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing",
            startDate = "",
            endDate = "",
            genres = "",
            formatAndYear = ""
        ),
        Anime(
            title = Title("Anime 3"),
            coverImage = Image(extraLarge = "", large = ""),
            status = "Ongoing",
            startDate = "",
            endDate = "",
            genres = "",
            formatAndYear = ""
        )
    )
    val homeItems = mutableListOf<HomeItems>().apply {
        add(
            HomeItems.TopAnime(
                topAnime = animeList,
                request = AnimeRequest.TrendingAnime
            )
        )
        add(
            HomeItems.TrendingAnime(
                trendingAnime = animeList,
                request = AnimeRequest.AllTimePopular
            )
        )
    }

    AnimeList(
        homeItems = homeItems
    )
}
