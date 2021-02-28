package com.yukarlo.feature.anime.home

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeRequest

internal sealed class HomeItems {

    internal data class TopAnime(
        val topAnime: List<Anime>?,
        val request: AnimeRequest
    ) : HomeItems()

    internal data class TrendingAnime(
        val trendingAnime: List<Anime>?,
        val request: AnimeRequest
    ) : HomeItems()
}
