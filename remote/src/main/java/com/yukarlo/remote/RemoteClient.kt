package com.yukarlo.remote

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.MultipleAnimeSort
import kotlinx.coroutines.flow.Flow
import type.MediaSeason
import type.MediaSort

interface RemoteClient {
    fun getAnimeFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<List<Anime>?>

    fun getAnimeCallbackFlow(
        page: Int,
        itemsPerPage: Int,
        year: Int?,
        season: MediaSeason?,
        sort: List<MediaSort>?
    ): Flow<List<Anime>?>

    fun getMultipleAnimeSortFlow(
        year: Int?,
        season: MediaSeason?
    ): Flow<MultipleAnimeSort?>
}
