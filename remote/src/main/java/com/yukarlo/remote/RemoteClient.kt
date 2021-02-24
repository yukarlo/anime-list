package com.yukarlo.remote

import kotlinx.coroutines.flow.Flow
import query.TopAnimesQuery
import type.MediaSeason
import type.MediaSort

interface RemoteClient {
    fun getTopAnimeFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<TopAnimesQuery.Page>

    fun getTopAnimeCallbackFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<TopAnimesQuery.Page>
}
