package com.yukarlo.feature.anime.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Title
import com.yukarlo.freenow.common.android.compose.layout.AnimeCard
import com.yukarlo.freenow.common.android.compose.layout.LazyGrid
import com.yukarlo.freenow.common.android.ui.theme.AnimeTheme

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(context = requireContext()).apply {
        setContent {
            AnimeTheme {
                Scaffold(
                    topBar = {
                        ToolBar(title = "Anime List")
                    }
                ) {
                    val animeList = listOf(
                        Anime(
                            title = Title(english = "Cowboy Bebop"),
                            coverImage = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1-CXtrrkMpJ8Zq.png",
                            status = "Ongoing"
                        ),
                        Anime(
                            title = Title(english = "Naruto"),
                            coverImage = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/nx20-KCjCtnUTsLcu.jpg",
                            status = "Ongoing"
                        ),
                        Anime(
                            title = Title(english = "Kuroko no Basket"),
                            coverImage = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx11771-82QwcjXwGoeg.jpg",
                            status = "Finished"
                        ),
                        Anime(
                            title = Title(english = "The Seven Deadly Sins"),
                            coverImage = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx20789-SsAI4pvvI9EU.jpg",
                            status = "Ongoing"
                        ),
                        Anime(
                            title = Title("Fullmetal Alchemist"),
                            coverImage = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx71-Fi08vs7xNBMW.png",
                            status = "Finished"
                        ),
                        Anime(
                            title = Title("Attack on Titan"),
                            coverImage = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx16498-m5ZMNtFioc7j.png",
                            status = "Ongoing"
                        )
                    )
                    AnimeList(animeList = animeList)
                }
            }
        }
    }

    @Composable
    fun ToolBar(title: String) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
            },
            backgroundColor = MaterialTheme.colors.primaryVariant
        )
    }

    @Composable
    fun AnimeList(animeList: List<Anime>) {
        LazyGrid(items = animeList, rows = 2, headerText = "Top Anime") { it: Anime, index: Int ->
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
