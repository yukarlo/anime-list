package com.yukarlo.feature.anime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result.*
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.AnimeSeason
import com.yukarlo.anime.core.model.AnimeSortTypes
import com.yukarlo.anime.lib.anime.domain.GetAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase
) : ViewModel() {

    private val updateHome: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val onUpdateHome: StateFlow<HomeUiState> = updateHome

    private val navigate: MutableSharedFlow<AnimeInputModel> = MutableSharedFlow()
    val onNavigate: SharedFlow<AnimeInputModel> = navigate

    init {
        fetchAnime()
    }

    private fun fetchAnime() {
        viewModelScope.launch {
            combine(
                getAnimeUseCase.execute(
                    param = AnimeParam(
                        sort = AnimeSortTypes.AllTimePopular.sortRequest
                    )
                ),
                getAnimeUseCase.execute(
                    param = AnimeParam(
                        sort = AnimeSortTypes.TrendingAnime.sortRequest,
                        year = 2021,
                        season = AnimeSeason.SPRING
                    )
                )
            ) { topAnime: List<Anime>?, trendingAnime: List<Anime>? ->

                val homeItems = mutableListOf<HomeItems>().apply {
                    add(
                        HomeItems.TopAnime(
                            topAnime = topAnime,
                            sortParameter = AnimeSortTypes.AllTimePopular
                        )
                    )
                    add(
                        HomeItems.TrendingAnime(
                            trendingAnime = trendingAnime,
                            sortParameter = AnimeSortTypes.TrendingAnime
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
        fetchAnime()
    }

    fun navigateTo(sortType: AnimeSortTypes) {
        viewModelScope.launch {
            val inputModel = when (sortType) {
                AnimeSortTypes.TrendingAnime -> {
                    AnimeInputModel(
                        sort = sortType,
                        year = 2021,
                        season = AnimeSeason.SPRING
                    )
                }
                else -> {
                    AnimeInputModel(
                        sort = sortType,
                        year = null,
                        season = null
                    )
                }
            }
            navigate.emit(inputModel)
        }
    }
}
