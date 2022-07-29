package com.yukarlo.anime.feature.anime.list.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.feature.domain.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAnimeUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    fun execute(param: AnimeParam): Flow<List<Anime>?> = animeRepository.fetchAnime(param = param)
}
