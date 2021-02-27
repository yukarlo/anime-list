package com.yukarlo.anime.core.model

data class Anime(
    val title: Title,
    val coverImage: Image,
    val genres: String?,
    val startDate: String,
    val endDate: String,
    val status: String,
    val formatAndYear: String
)
