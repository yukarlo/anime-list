package com.yukarlo.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.yukarlo.AnimeDetailsQuery
import com.yukarlo.AnimeQuery
import com.yukarlo.MultipleAnimeSortQuery
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.MultipleAnimeSort
import com.yukarlo.type.MediaSeason
import com.yukarlo.type.MediaSort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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
                page = Optional.presentIfNotNull(value = page),
                itemsPerPage = Optional.presentIfNotNull(value = itemsPerPage),
                year = Optional.presentIfNotNull(value = year),
                season = Optional.presentIfNotNull(value = season),
                sort = Optional.presentIfNotNull(value = sort)
            )

            apolloClient.query(animeQuery).toFlow().collect { response ->
                response.data?.animePage?.let {
                    val result = animeMapper.mapAnimeToDomain(result = it.media)
                    emit(result)
                }
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
//            val animeQuery = AnimeQuery(
//                page = Optional.presentIfNotNull(value = page),
//                itemsPerPage = Optional.presentIfNotNull(value = itemsPerPage),
//                year = Optional.presentIfNotNull(value = year),
//                season = Optional.presentIfNotNull(value = season),
//                sort = Optional.presentIfNotNull(value = sort)
//            )
//            apolloClient.query(animeQuery).enqueue(
//                object : ApolloCall.Callback<AnimeQuery.Data>() {
//                    override fun onResponse(response: ApolloResponse<AnimeQuery.Data>) {
//                        response.data?.animePage?.let {
//                            val result = animeMapper.mapAnimeToDomain(result = it.media)
//                            trySend(result)
//                        }
//                    }
//
//                    override fun onFailure(e: ApolloException) {
//                        close(e)
//                    }
//
//                    override fun onStatusEvent(event: ApolloCall.StatusEvent) {
//                        if (event == ApolloCall.StatusEvent.COMPLETED) {
//                            close()
//                        }
//                    }
//
//                }
//            )
//            awaitClose { this.cancel() }
        }

    override fun getMultipleAnimeSortFlow(
        year: Int?,
        season: MediaSeason?
    ): Flow<MultipleAnimeSort> =
        flow {
            val animeQuery = MultipleAnimeSortQuery(
                year = Optional.presentIfNotNull(value = year),
                season = Optional.presentIfNotNull(value = season)
            )

            apolloClient.query(animeQuery).toFlow().collect { response ->
                response.data?.let {
                    val result = animeMapper.mapMultipleAnimeToDomain(data = it)
                    emit(result)
                }
            }
        }

    override fun getAnimeDetails(animeId: Int): Flow<AnimeDetails> = flow {
        val animeQuery = AnimeDetailsQuery(
            id = Optional.presentIfNotNull(value = animeId)
        )

        apolloClient.query(animeQuery).toFlow().collect { response ->
            response.data?.let {
                val result = animeMapper.mapAnimeDetailsToDomain(result = it.animeMedia)
                emit(result)
            }
        }
    }
}
