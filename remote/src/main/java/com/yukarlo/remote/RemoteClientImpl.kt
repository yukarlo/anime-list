package com.yukarlo.remote

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.MultipleAnimeSort
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import query.AnimeDetailsQuery
import query.AnimeQuery
import query.MultipleAnimeSortQuery
import type.MediaSeason
import type.MediaSort
import javax.inject.Inject

internal class RemoteClientImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val animeMapper: AnimeMapper
) : RemoteClient {

    override fun getAnimeFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<List<Anime>?> =
        flow {
            val animeQuery = AnimeQuery(
                page = Input.optional(value = page),
                itemsPerPage = Input.optional(value = itemsPerPage),
                year = Input.optional(value = year),
                season = Input.optional(value = season),
                sort = Input.optional(value = sort)
            )

            val response = apolloClient.query(animeQuery).await()
            response.data?.page?.let {
                val result = animeMapper.mapAnimeToDomain(result = it.media)
                emit(result)
            }
        }

    override fun getAnimeCallbackFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<List<Anime>?> =
        callbackFlow {
            val animeQuery = AnimeQuery(
                page = Input.optional(value = page),
                itemsPerPage = Input.optional(value = itemsPerPage),
                year = Input.optional(value = year),
                season = Input.optional(value = season),
                sort = Input.optional(value = sort)
            )
            apolloClient.query(animeQuery).enqueue(
                object : ApolloCall.Callback<AnimeQuery.Data>() {
                    override fun onResponse(response: Response<AnimeQuery.Data>) {
                        response.data?.page?.let {
                            val result = animeMapper.mapAnimeToDomain(result = it.media)
                            offer(result)
                        }
                    }

                    override fun onFailure(e: ApolloException) {
                        close(e)
                    }

                    override fun onStatusEvent(event: ApolloCall.StatusEvent) {
                        if (event == ApolloCall.StatusEvent.COMPLETED) {
                            close()
                        }
                    }

                }
            )
            awaitClose { this.cancel() }
        }

    override fun getMultipleAnimeSortFlow(
        year: Int?,
        season: MediaSeason?
    ): Flow<MultipleAnimeSort> =
        flow {
            val animeQuery = MultipleAnimeSortQuery(
                year = Input.optional(value = year),
                season = Input.optional(value = season)
            )

            val response = apolloClient.query(animeQuery).await()
            response.data?.let {
                val result = animeMapper.mapMultipleAnimeToDomain(data = it)
                emit(result)
            }
        }

    override fun getAnimeDetails(animeId: Int): Flow<AnimeDetails> = flow {
        val animeQuery = AnimeDetailsQuery(
            id = Input.optional(value = animeId)
        )

        val response = apolloClient.query(animeQuery).await()
        response.data?.let {
            val result = animeMapper.mapAnimeDetailsToDomain(result = it.media)
            emit(result)
        }
    }
}
