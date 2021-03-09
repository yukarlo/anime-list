package com.yukarlo.anime.core.model

data class AnimeDetails(
    val basicInfo: Anime = Anime(),
    val averageScore: Int = 0,
    val description: String = "",
    val characters: List<Character> = emptyList(),
    val episodes: Int = 0,
    val duration: Int = 0,
    val studio: String = "",
    val recommendations: List<Anime> = emptyList(),
    val trailer: Trailer = Trailer()
)
