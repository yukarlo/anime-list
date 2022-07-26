package com.yukarlo.feature.anime.home.domain

import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.MultipleAnimeSort
import com.yukarlo.feature.domain.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMultipleAnimeSortUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    fun execute(param: AnimeParam): Flow<MultipleAnimeSort> =
        animeRepository.fetchMultipleAnimeSort(param = param)
}
