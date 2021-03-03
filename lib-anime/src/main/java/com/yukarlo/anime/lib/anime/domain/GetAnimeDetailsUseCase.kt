package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend fun execute(param: Int): Flow<AnimeDetails> = animeRepository.fetchAnimeDetails(id = param)
}
