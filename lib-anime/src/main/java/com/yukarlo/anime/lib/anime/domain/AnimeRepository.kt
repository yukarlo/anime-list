package com.yukarlo.anime.lib.anime.domain

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.MultipleAnimeSort
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun fetchAnime(param: AnimeParam): Flow<List<Anime>?>
    fun fetchMultipleAnimeSort(param: AnimeParam): Flow<MultipleAnimeSort>
    fun fetchAnimeDetails(id: Int): Flow<AnimeDetails>
}
