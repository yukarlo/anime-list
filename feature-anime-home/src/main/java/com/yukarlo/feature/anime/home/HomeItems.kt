package com.yukarlo.feature.anime.home

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeSortTypes

internal sealed class HomeItems {

    internal data class TopAnime(
        val topAnime: List<Anime>?,
        val sortParameter: AnimeSortTypes
    ) : HomeItems()

    internal data class TrendingAnime(
        val trendingAnime: List<Anime>?,
        val sortParameter: AnimeSortTypes
    ) : HomeItems()
}
