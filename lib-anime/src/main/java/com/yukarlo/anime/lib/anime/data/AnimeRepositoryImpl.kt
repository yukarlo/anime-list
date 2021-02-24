package com.yukarlo.anime.lib.anime.data

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeParam
import com.yukarlo.anime.lib.anime.domain.AnimeRepository
import com.yukarlo.anime.lib.anime.mapper.AnimeMapper
import com.yukarlo.remote.RemoteClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AnimeRepositoryImpl @Inject constructor(
    private val remoteClient: RemoteClient,
    private val mapper: AnimeMapper
) : AnimeRepository {

    override suspend fun fetchTopAnime(param: TopAnimeParam): Flow<List<Anime>?> =
        remoteClient.getTopAnimeFlow(page = param.page, itemsPerPage = param.itemsPerPage)
            .map {
                mapper.mapToDomain(result = it.media)
            }
}
