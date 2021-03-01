package com.yukarlo.remote

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.yukarlo.anime.core.model.Anime
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import query.AnimeQuery
import type.MediaSeason
import type.MediaSort
import javax.inject.Inject

internal class RemoteClientImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val animeMapper: AnimeMapper
) : RemoteClient {

    override fun getTopAnimeFlow(
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
                val result = animeMapper.mapTopAnimeToDomain(result = it.media)
                emit(result)
            }
        }

    override fun getTopAnimeCallbackFlow(
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
                            val result = animeMapper.mapTopAnimeToDomain(result = it.media)
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
}
