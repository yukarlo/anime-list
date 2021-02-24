package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAnimeUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend fun execute(param: AnimeParam): Flow<List<Anime>?> = animeRepository.fetchTopAnime(param = param)
}
