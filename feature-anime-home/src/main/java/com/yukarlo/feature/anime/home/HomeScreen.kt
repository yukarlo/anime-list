package com.yukarlo.feature.anime.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.common.android.components.*
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.*
import dev.chrisbanes.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewAll: (AnimeInputModel) -> Unit,
    navigateToDetails: (Int?) -> Unit,
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
            homeItems = homeState.homeItems,
            viewAll = viewModel::navigateTo,
            onAnimeClick = navigateToDetails
        )
    }
}

@Composable
private fun AnimeList(
    homeItems: LinkedHashMap<AnimeSortTypes, List<Anime>>,
    viewAll: (AnimeSortTypes) -> Unit,
    onAnimeClick: (Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        Box {
            homeItems[AnimeSortTypes.Top10]?.let {
                AnimeWithTextOverlay(
                    anime = it.random(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                contentColor = Color.White,
                modifier = Modifier.systemBarsPadding()
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        }
        homeItems.map { (sort: AnimeSortTypes, anime: List<Anime>) ->
            ListHeaderTitle(
                title = sort.title,
                viewAll = {
                    viewAll(sort)
                }
            )

            HorizontalList(
                items = anime
            ) { item: Anime, modifier: Modifier ->
                AnimeCard(
                    anime = item,
                    modifier = modifier
                        .width(width = 140.dp)
                        .height(height = 260.dp),
                    onClick = {
                        onAnimeClick(it)
                    }
                )
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
    val animeHashMap = LinkedHashMap<AnimeSortTypes, List<Anime>>()
    animeHashMap[AnimeSortTypes.TrendingAnime] = animeList
    animeHashMap[AnimeSortTypes.AllTimePopular] = animeList
    animeHashMap[AnimeSortTypes.Top10] = animeList

    AnimeList(
        homeItems = animeHashMap,
        viewAll = { },
        onAnimeClick = { }
    )
}
