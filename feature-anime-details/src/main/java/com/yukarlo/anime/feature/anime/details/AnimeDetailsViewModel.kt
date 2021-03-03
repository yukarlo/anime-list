package com.yukarlo.anime.feature.anime.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.lib.anime.domain.GetAnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AnimeDetailsViewModel @Inject constructor(
    private val animeDetailsUseCase: GetAnimeDetailsUseCase
) : ViewModel() {

    private val animeDetails: MutableStateFlow<AnimeDetailsUiState> =
        MutableStateFlow(AnimeDetailsUiState())
    val onAnimeDetails: StateFlow<AnimeDetailsUiState> = animeDetails

    fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            animeDetailsUseCase.execute(param = animeId)
                .onStart {
                    animeDetails.value = AnimeDetailsUiState(
                        result = Result.LOADING
                    )
                }
                .catch {
                    animeDetails.value = AnimeDetailsUiState(
                        animeDetails = animeDetails.value.animeDetails
                    )
                }
                .collect {
                    animeDetails.value = AnimeDetailsUiState(
                        result = Result.SUCCESS,
                        animeDetails = it
                    )
                }
        }
    }
}
