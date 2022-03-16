package com.yukarlo.anime.feature.anime.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.common.android.navigation.AnimeInputModel
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.lib.anime.domain.GetAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AnimeListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeUseCase: GetAnimeUseCase
) : ViewModel() {

    private val animeModel: AnimeInputModel = savedStateHandle.get("AnimeInputModelKey")!!

    private val updateAnimeList: MutableStateFlow<AnimeListUiState> =
        MutableStateFlow(AnimeListUiState())
    val onUpdateAnimeList: StateFlow<AnimeListUiState> = updateAnimeList

    private var page = 1

    init {
        fetchAnime(animeModel)
    }

    private fun fetchAnime(inputModel: AnimeInputModel?) {
        viewModelScope.launch {
            getAnimeUseCase.execute(
                param = AnimeParam(
                    sort = inputModel?.sort?.sortRequest.orEmpty(),
                    year = inputModel?.year,
                    season = inputModel?.season,
                    page = page
                )
            )
                .onStart {
                    if (page == 1) {
                        updateAnimeList.value = AnimeListUiState(
                            result = Result.LOADING,
                            toolbarTitle = "",
                            animeList = emptyList()
                        )
                    }
                }
                .catch {
                    updateAnimeList.value = AnimeListUiState(
                        result = Result.ERROR,
                        toolbarTitle = "",
                        animeList = updateAnimeList.value.animeList
                    )
                }
                .collect { newAnime ->
                    page++
                    updateAnimeList.value = AnimeListUiState(
                        result = Result.SUCCESS,
                        toolbarTitle = inputModel?.sort?.title.orEmpty(),
                        animeList = newAnime.orEmpty().let {
                            updateAnimeList.value.animeList + it
                        }
                    )
                }
        }
    }

    fun retry() {
        fetchAnime(inputModel = animeModel)
    }

    fun fetchNewPage() {
        fetchAnime(inputModel = animeModel)
    }
}
