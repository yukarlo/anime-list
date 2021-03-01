package com.yukarlo.feature.anime.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.yukarlo.anime.common.android.components.AnimeCard
import com.yukarlo.anime.common.android.components.AnimeWithTextOverlay
import com.yukarlo.anime.common.android.components.ListHeaderTitle
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.navigation.NavigationScreens
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeSortTypes
import com.yukarlo.anime.core.model.Image
import com.yukarlo.anime.core.model.Title
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController
) {
    val factory = HiltViewModelFactory(
        context = LocalContext.current,
        navBackStackEntry = navBackStackEntry
    )
    val viewModel: HomeViewModel = viewModel(
        key = HomeViewModel::class.java.simpleName,
        factory = factory
    )

    val scope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        scope.launch {
            viewModel.onNavigate
                .collect {
                    navController.apply {
                        currentBackStackEntry
                            ?.arguments?.putParcelable(
                                NavigationScreens.ViewAllAnime.parcelableKey,
                                it
                            )
                        navigate(route = NavigationScreens.ViewAllAnime.route)
                    }
                }
        }
        onDispose {
            viewModel
        }
    }

    viewModel.onUpdateHome.collectAsState().value.let { homeState ->
        ScreenState(
            result = homeState.result,
            retry = {
                viewModel.retry()
            },
            renderView = {
                AnimeList(
                    homeItems = homeState.homeItems,
                    viewAll = {
                        viewModel.navigateTo(sortType = it)
                    }
                )
            }
        )
    }
}

@Composable
private fun AnimeList(
    homeItems: LinkedHashMap<AnimeSortTypes, List<Anime>>,
    viewAll: (AnimeSortTypes) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        homeItems[AnimeSortTypes.Top10]?.let {
            AnimeWithTextOverlay(anime = it.random())
        }
        homeItems.map { (sort: AnimeSortTypes, anime: List<Anime>) ->
            ListHeaderTitle(
                title = sort.title,
                viewAll = {
                    viewAll(sort)
                }
            )

            LazyRow(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ) {
                itemsIndexed(items = anime) { _, it ->
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
    val animeHashMap = LinkedHashMap<AnimeSortTypes, List<Anime>>()
    animeHashMap[AnimeSortTypes.TrendingAnime] = animeList
    animeHashMap[AnimeSortTypes.AllTimePopular] = animeList
    animeHashMap[AnimeSortTypes.Top10] = animeList

    AnimeList(
        homeItems = animeHashMap,
        viewAll = { }
    )
}
