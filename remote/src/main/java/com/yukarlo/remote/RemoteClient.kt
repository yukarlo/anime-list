package com.yukarlo.remote

import kotlinx.coroutines.flow.Flow
import query.TopAnimesQuery

interface RemoteClient {
    fun getTopAnimeFlow(page: Int, itemsPerPage: Int): Flow<TopAnimesQuery.Page>
    fun getTopAnimeCallbackFlow(page: Int, itemsPerPage: Int): Flow<TopAnimesQuery.Page>
}
