package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun fetchTopAnime(param: AnimeParam): Flow<List<Anime>?>
}
