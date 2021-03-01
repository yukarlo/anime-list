package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.MultipleAnimeSort
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun fetchAnime(param: AnimeParam): Flow<List<Anime>?>
    suspend fun fetchMultipleAnimeSort(param: AnimeParam): Flow<MultipleAnimeSort>
}
