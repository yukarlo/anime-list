package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.MultipleAnimeSort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMultipleAnimeSortUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend fun execute(param: AnimeParam): Flow<MultipleAnimeSort> =
        animeRepository.fetchMultipleAnimeSort(param = param)
}
