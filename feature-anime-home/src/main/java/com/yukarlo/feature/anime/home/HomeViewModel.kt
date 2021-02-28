package com.yukarlo.feature.anime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result.*
import com.yukarlo.anime.core.model.*
import com.yukarlo.anime.lib.anime.domain.GetTopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase
) : ViewModel() {

    private val updateHome: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val onUpdateHome: StateFlow<HomeUiState> = updateHome

    init {
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            combine(
                getTopAnimeUseCase.execute(
                    param = AnimeParam(
                        sort = AnimeRequest.AllTimePopular.sortRequest
                    )
                ),
                getTopAnimeUseCase.execute(
                    param = AnimeParam(
                        sort = AnimeRequest.TrendingAnime.sortRequest,
                        year = 2021,
                        season = AnimeSeason.SPRING
                    )
                )
            ) { topAnime: List<Anime>?, trendingAnime: List<Anime>? ->

                val homeItems = mutableListOf<HomeItems>().apply {
                    add(
                        HomeItems.TopAnime(
                            topAnime = topAnime,
                            request = AnimeRequest.AllTimePopular
                        )
                    )
                    add(
                        HomeItems.TrendingAnime(
                            trendingAnime = trendingAnime,
                            request = AnimeRequest.TrendingAnime
                        )
                    )
                }
                updateHome.value = HomeUiState(
                    result = SUCCESS,
                    homeItems = homeItems
                )
            }
                .onStart {
                    updateHome.value = HomeUiState(
                        result = LOADING,
                        homeItems = emptyList()
                    )
                }
                .catch {
                    updateHome.value = HomeUiState(
                        result = ERROR,
                        homeItems = updateHome.value.homeItems
                    )
                }
                .collect()
        }
    }

    fun retry() {
        fetchTopAnime()
    }
}
