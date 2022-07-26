package com.yukarlo.anime.core.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.yukarlo.AnimeDetailsQuery
import com.yukarlo.AnimeQuery
import com.yukarlo.MultipleAnimeSortQuery
import com.yukarlo.type.MediaSeason
import com.yukarlo.type.MediaSort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RemoteClientImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : RemoteClient {

    override fun getAnimeFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<ApolloResponse<AnimeQuery.Data>> {
        val animeQuery = AnimeQuery(
            page = Optional.presentIfNotNull(value = page),
            itemsPerPage = Optional.presentIfNotNull(value = itemsPerPage),
            year = Optional.presentIfNotNull(value = year),
            season = Optional.presentIfNotNull(value = season),
            sort = Optional.presentIfNotNull(value = sort)
        )

        return apolloClient.query(animeQuery).toFlow()
    }

    override fun getMultipleAnimeSortFlow(
        year: Int?,
        season: MediaSeason?
    ): Flow<ApolloResponse<MultipleAnimeSortQuery.Data>> {
        val animeQuery = MultipleAnimeSortQuery(
            year = Optional.presentIfNotNull(value = year),
            season = Optional.presentIfNotNull(value = season)
        )

        return apolloClient.query(animeQuery).toFlow()
    }

    override fun getAnimeDetails(animeId: Int): Flow<ApolloResponse<AnimeDetailsQuery.Data>> {
        val animeQuery = AnimeDetailsQuery(
            id = Optional.presentIfNotNull(value = animeId)
        )

        return apolloClient.query(animeQuery).toFlow()
    }
}
