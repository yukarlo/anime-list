package com.yukarlo.feature.anime.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.yukarlo.anime.common.android.base.BaseFragment
import com.yukarlo.anime.common.android.compose.layout.*
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Image
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
                ScreenState(
                    result = homeState.result,
                    retry = {
                        viewModel.retry()
                    },
                    renderView = {
                        AnimeList(
                            animeList = homeState.homeItems,
                            animeBanner = homeState.homeAnimeBanner
                        )
                    }
                )
            }
        }
    }

    @Composable
    fun AnimeList(animeList: List<Anime>, animeBanner: Anime?) {
        Column {
            LazyGrid(
                items = animeList,
                rows = 3,
                headerText = "ALL TIME TOP ANIME",
                viewAll = {
                    Toast.makeText(requireContext(), "view all", Toast.LENGTH_LONG).show()
                },
                itemContent = { it: Anime, index: Int ->
                    DisposableEffect(Unit) {
                        if (index == animeList.lastIndex) {
                            viewModel.requestNextPage()
                        }
                        onDispose {
                            viewModel
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
            )
        )
    }
}
