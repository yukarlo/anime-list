package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend fun execute(param: AnimeParam): Flow<List<Anime>?> = animeRepository.fetchAnime(param = param)
}
