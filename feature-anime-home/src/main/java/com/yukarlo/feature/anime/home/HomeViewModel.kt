package com.yukarlo.feature.anime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result.*
import com.yukarlo.anime.core.model.TopAnimeParam
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
        fetTopAnime()
    }

    private fun fetTopAnime() {
        viewModelScope.launch {
            getTopAnimeUseCase.execute(
                param = TopAnimeParam(page = 1)
            )
                .onStart {
                    updateHome.value = HomeUiState(
                        result = LOADING,
                        homeItems = listOf()
                    )
                }
                .catch {
                    updateHome.value = HomeUiState(
                        result = ERROR,
                        homeItems = listOf()
                    )
                }
                .collect {
                    updateHome.value = HomeUiState(
                        result = SUCCESS,
                        homeItems = it ?: listOf()
                    )
                }
        }
    }
}
