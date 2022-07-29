package com.yukarlo.anime.feature.anime.details.domain

import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.feature.domain.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAnimeDetailsUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    fun execute(param: Int): Flow<AnimeDetails> = animeRepository.fetchAnimeDetails(id = param)
}
