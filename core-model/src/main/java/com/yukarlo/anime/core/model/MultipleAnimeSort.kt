package com.yukarlo.anime.core.model

data class MultipleAnimeSort(
    val top100: List<Anime>,
    val popularThisSeason: List<Anime>,
    val trendingNow: List<Anime>,
    val allTimePopular: List<Anime>,
)