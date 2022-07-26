package com.yukarlo.anime.core.remote

import com.apollographql.apollo3.api.ApolloResponse
import com.yukarlo.AnimeDetailsQuery
import com.yukarlo.AnimeQuery
import com.yukarlo.MultipleAnimeSortQuery
import com.yukarlo.type.MediaSeason
import com.yukarlo.type.MediaSort
import kotlinx.coroutines.flow.Flow

interface RemoteClient {
    fun getAnimeFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<ApolloResponse<AnimeQuery.Data>>

    fun getMultipleAnimeSortFlow(
        year: Int?,
        season: MediaSeason?
    ): Flow<ApolloResponse<MultipleAnimeSortQuery.Data>>

    fun getAnimeDetails(
        animeId: Int
    ): Flow<ApolloResponse<AnimeDetailsQuery.Data>>
}
