package com.yukarlo.remote

import com.yukarlo.anime.core.model.Anime
import kotlinx.coroutines.flow.Flow
import type.MediaSeason
import type.MediaSort

interface RemoteClient {
    fun getTopAnimeFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<List<Anime>?>

    fun getTopAnimeCallbackFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<List<Anime>?>
}
