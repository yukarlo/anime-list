package com.yukarlo.remote.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideOkHttpBuilder() = OkHttpClient.Builder().build()

    @Provides
    fun provideServerUrl(): String = "https://graphql.anilist.co"

    @Provides
    fun provideApolloClient(
        okHttpClient: OkHttpClient,
        serverUrl: String
    ): ApolloClient =
        ApolloClient.builder()
            .serverUrl(serverUrl)
            .okHttpClient(okHttpClient)
            .build()
}