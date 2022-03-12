package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.AnimeDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    fun execute(param: Int): Flow<AnimeDetails> = animeRepository.fetchAnimeDetails(id = param)
}
