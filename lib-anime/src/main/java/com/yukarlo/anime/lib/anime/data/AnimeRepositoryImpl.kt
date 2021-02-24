package com.yukarlo.anime.lib.anime.data

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.lib.anime.domain.AnimeRepository
import com.yukarlo.anime.lib.anime.mapper.AnimeMapper
import com.yukarlo.anime.lib.anime.mapper.SeasonMapper
import com.yukarlo.anime.lib.anime.mapper.SortMapper
import com.yukarlo.remote.RemoteClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AnimeRepositoryImpl @Inject constructor(
    private val remoteClient: RemoteClient,
    private val mapper: AnimeMapper,
    private val seasonMapper: SeasonMapper,
    private val sortMapper: SortMapper
) : AnimeRepository {

    override suspend fun fetchAnime(param: AnimeParam): Flow<List<Anime>?> =
        remoteClient.getTopAnimeFlow(
            page = param.page,
            itemsPerPage = param.itemsPerPage,
            year = param.year,
            season = seasonMapper.mapSeason(animeSeason = param.season),
            sort = sortMapper.mapSort(animeSort = param.sort)
        )
            .map {
                mapper.mapTopAnimeToDomain(result = it.media)
            }
}
