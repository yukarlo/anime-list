package com.yukarlo.anime.lib.anime.data

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.MultipleAnimeSort
import com.yukarlo.anime.lib.anime.domain.AnimeRepository
import com.yukarlo.anime.lib.anime.mapper.SeasonMapper
import com.yukarlo.anime.lib.anime.mapper.SortMapper
import com.yukarlo.remote.RemoteClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AnimeRepositoryImpl @Inject constructor(
    private val remoteClient: RemoteClient,
    private val seasonMapper: SeasonMapper,
    private val sortMapper: SortMapper
) : AnimeRepository {

    override fun fetchAnime(param: AnimeParam): Flow<List<Anime>?> =
        remoteClient.getAnimeFlow(
            page = param.page,
            itemsPerPage = param.itemsPerPage,
            year = param.year,
            season = seasonMapper.mapSeason(animeSeason = param.season),
            sort = sortMapper.mapSort(animeSort = param.sort)
        )

    override fun fetchMultipleAnimeSort(param: AnimeParam): Flow<MultipleAnimeSort> =
        remoteClient.getMultipleAnimeSortFlow(
            year = param.year,
            season = seasonMapper.mapSeason(animeSeason = param.season)
        )

    override fun fetchAnimeDetails(id: Int): Flow<AnimeDetails> =
        remoteClient.getAnimeDetails(animeId = id)
}
