package com.yukarlo.anime.lib.anime.data

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.core.model.MultipleAnimeSort
import com.yukarlo.anime.core.remote.RemoteClient
import com.yukarlo.anime.lib.anime.mapper.AnimeMapper
import com.yukarlo.anime.lib.anime.mapper.SeasonMapper
import com.yukarlo.anime.lib.anime.mapper.SortMapper
import com.yukarlo.feature.domain.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

internal class AnimeRepositoryImpl @Inject constructor(
    private val remoteClient: RemoteClient,
    private val seasonMapper: SeasonMapper,
    private val sortMapper: SortMapper,
    private val animeMapper: AnimeMapper
) : AnimeRepository {

    override fun fetchAnime(param: AnimeParam): Flow<List<Anime>> =
        remoteClient.getAnimeFlow(
            page = param.page,
            itemsPerPage = param.itemsPerPage,
            year = param.year,
            season = seasonMapper.mapSeasonToData(animeSeason = param.season),
            sort = sortMapper.mapSortToData(animeSort = param.sort)
        )
            .mapNotNull { animeMapper.mapAnimeToDomain(it.data?.animePage?.media) }
            .flowOn(Dispatchers.IO)

    override fun fetchMultipleAnimeSort(param: AnimeParam): Flow<MultipleAnimeSort> =
        remoteClient.getMultipleAnimeSortFlow(
            year = param.year,
            season = seasonMapper.mapSeasonToData(animeSeason = param.season)
        )
            .mapNotNull { animeMapper.mapMultipleAnimeToDomain(it.data) }
            .flowOn(Dispatchers.IO)

    override fun fetchAnimeDetails(id: Int): Flow<AnimeDetails> =
        remoteClient.getAnimeDetails(animeId = id)
            .mapNotNull { animeMapper.mapAnimeDetailsToDomain(it.data?.animeMedia) }
            .flowOn(Dispatchers.IO)
}
