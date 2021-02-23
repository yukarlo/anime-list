package com.yukarlo.remote

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import query.TopAnimesQuery
import javax.inject.Inject

internal class RemoteClientImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : RemoteClient {

    override fun getTopAnimeFlow(page: Int): Flow<TopAnimesQuery.Page> =
        flow {
            val topAnimeQuery = TopAnimesQuery(
                page = Input.optional(value = page)
            )

            val response = apolloClient.query(topAnimeQuery).await()
            response.data?.page?.let {
                emit(it)
            }
        }

    override fun getTopAnimeCallbackFlow(page: Int): Flow<TopAnimesQuery.Page> = callbackFlow {
        val topAnimeQuery = TopAnimesQuery(
            page = Input.optional(value = page)
        )
        apolloClient.query(topAnimeQuery).enqueue(
            object : ApolloCall.Callback<TopAnimesQuery.Data>() {
                override fun onResponse(response: Response<TopAnimesQuery.Data>) {
                    response.data?.page?.let {
                        offer(it)
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
