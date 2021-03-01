package com.yukarlo.feature.anime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result.*
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.*
import com.yukarlo.anime.lib.anime.domain.GetMultipleAnimeSortUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getMultipleAnimeSortUseCase: GetMultipleAnimeSortUseCase
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
            getMultipleAnimeSortUseCase.execute(
                param = AnimeParam(
                    year = 2021,
                    season = AnimeSeason.WINTER
                )
            )
                .onStart {
                    updateHome.value = HomeUiState(
                        result = LOADING,
                        homeItems = linkedMapOf()
                    )
                }
                .catch {
                    updateHome.value = HomeUiState(
                        result = ERROR,
                        homeItems = updateHome.value.homeItems
                    )
                }.collect {
                    val homeItems = collectAnime(anime = it)

                    updateHome.value = HomeUiState(
                        result = SUCCESS,
                        homeItems = homeItems
                    )
                }
        }
    }

    private fun collectAnime(anime: MultipleAnimeSort): LinkedHashMap<AnimeSortTypes, List<Anime>> =
        with(anime) {
            val animeHashMap = LinkedHashMap<AnimeSortTypes, List<Anime>>()

            if (trendingNow.isNotEmpty()) {
                animeHashMap[AnimeSortTypes.TrendingAnime] = trendingNow
            }
            if (popularThisSeason.isNotEmpty()) {
                animeHashMap[AnimeSortTypes.PopularThisSeason] = popularThisSeason
            }
            if (allTimePopular.isNotEmpty()) {
                animeHashMap[AnimeSortTypes.AllTimePopular] = allTimePopular
            }
            if (top10.isNotEmpty()) {
                animeHashMap[AnimeSortTypes.Top10] = top10
            }

            animeHashMap
        }

    fun retry() {
        fetchAnime()
    }

    fun navigateTo(sortType: AnimeSortTypes) {
        viewModelScope.launch {
            val inputModel = when (sortType) {
                AnimeSortTypes.PopularThisSeason -> {
                    AnimeInputModel(
                        sort = sortType,
                        year = 2021,
                        season = AnimeSeason.WINTER
                    )
                }
                else -> {
                    AnimeInputModel(
                        sort = sortType
                    )
                }
            }
            navigate.emit(inputModel)
        }
    }
}
