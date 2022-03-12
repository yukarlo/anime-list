package com.yukarlo.remote.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.yukarlo.remote.RemoteClient
import com.yukarlo.remote.RemoteClientImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteModule {

    @Binds
    abstract fun bindRemoteClient(
        remoteClient: RemoteClientImpl
    ): RemoteClient

    companion object {
        @Provides
        fun provideOkHttpBuilder() = OkHttpClient.Builder().build()

        @Provides
        fun provideServerUrl(): String = "https://graphql.anilist.co"

        @Provides
        fun provideApolloClient(
            okHttpClient: OkHttpClient,
            serverUrl: String
        ): ApolloClient =
            ApolloClient.Builder()
                .serverUrl(serverUrl)
                .okHttpClient(okHttpClient)
                .build()
    }
}
