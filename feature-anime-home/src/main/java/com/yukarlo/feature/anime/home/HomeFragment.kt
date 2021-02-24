package com.yukarlo.feature.anime.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.yukarlo.anime.common.android.base.BaseFragment
import com.yukarlo.anime.common.android.compose.layout.AnimeCard
import com.yukarlo.anime.common.android.compose.layout.LazyGrid
import com.yukarlo.anime.common.android.compose.layout.ScreenState
import com.yukarlo.anime.common.android.compose.layout.ToolBar
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Title
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    @Composable
    override fun SetContent() {
        viewModel.onUpdateHome.collectAsState().value.let { homeState ->
            Scaffold(
                topBar = {
                    ToolBar(title = "Anime List")
                }
            ) {
                ScreenState(result = homeState.result) {
                    AnimeList(animeList = homeState.homeItems)
                }
            }
        }
    }

    @Composable
    fun AnimeList(animeList: List<Anime>) {
        LazyGrid(items = animeList, rows = 3, headerText = "Top Anime") { it: Anime, index: Int ->
            DisposableEffect(Unit) {
                if (index == animeList.lastIndex) {
                    viewModel.requestNextPage()
                }
                onDispose {
                    viewModel
                }
            }
            AnimeCard(anime = it)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        val animeList = listOf(
            Anime(
                title = Title("Anime 1"),
                coverImage = "",
                status = "Ongoing"
            ),
            Anime(
                title = Title("Anime 2"),
                coverImage = "",
                status = "Ongoing"
            ),
            Anime(
                title = Title("Anime 3"),
                coverImage = "",
                status = "Ongoing"
            )
        )
        AnimeList(animeList = animeList)
    }
}
