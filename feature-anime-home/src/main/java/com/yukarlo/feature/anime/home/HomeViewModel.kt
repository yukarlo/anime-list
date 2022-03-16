package com.yukarlo.feature.anime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result.ERROR
import com.yukarlo.anime.common.android.base.Result.SUCCESS
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.common.android.utils.SeasonUtil
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.AnimeSortTypes
import com.yukarlo.anime.lib.anime.domain.GetMultipleAnimeSortUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getMultipleAnimeSortUseCase: GetMultipleAnimeSortUseCase,
    private val seasonUtil: SeasonUtil
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
                    year = seasonUtil.getYear(),
                    season = seasonUtil.getAstronomicalSeason()
                )
            )
                .onStart {
                    updateHome.value = HomeUiState()
                }
                .catch {
                    updateHome.value = HomeUiState(
                        result = ERROR,
                        trendingAnime = updateHome.value.trendingAnime,
                        popularThisSeasonAnime = updateHome.value.popularThisSeasonAnime,
                        allTimePopularAnime = updateHome.value.allTimePopularAnime,
                        topTenAnime = updateHome.value.topTenAnime
                    )
                }.collect {
                    updateHome.value = HomeUiState(
                        result = SUCCESS,
                        trendingAnime = it.trendingNow,
                        popularThisSeasonAnime = it.popularThisSeason,
                        allTimePopularAnime = it.allTimePopular,
                        topTenAnime = it.top10
                    )
                }
        }
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
                        year = seasonUtil.getYear(),
                        season = seasonUtil.getAstronomicalSeason()
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
