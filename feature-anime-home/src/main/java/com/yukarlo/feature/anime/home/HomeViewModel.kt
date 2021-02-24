package com.yukarlo.feature.anime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result.*
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.AnimeSort
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

    private var page = 1

    init {
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            getTopAnimeUseCase.execute(
                param = AnimeParam(
                    page = page,
                    sort = listOf(AnimeSort.SCORE_DESC)
                )
            )
                .onStart {
                    if (page == 1) {
                        updateHome.value = HomeUiState(
                            result = LOADING,
                            homeItems = listOf()
                        )
                    }
                }
                .catch {
                    page = 1
                    updateHome.value = HomeUiState(
                        result = ERROR,
                        homeItems = listOf()
                    )
                }
                .collect { newAnime ->
                    page++
                    updateHome.value = HomeUiState(
                        result = SUCCESS,
                        homeItems = newAnime?.let {
                            updateHome.value.homeItems + it
                        } ?: emptyList()
                    )
                }
        }
    }

    fun requestNextPage() {
        fetchTopAnime()
    }
}
