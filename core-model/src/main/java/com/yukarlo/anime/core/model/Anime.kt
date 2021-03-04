package com.yukarlo.anime.core.model

data class Anime(
    val id: Int? = null,
    val title: Title = Title(),
    val coverImage: Image = Image(),
    val genres: String? = null,
    val startDate: Date? = null,
    val endDate: Date? = null,
    val status: String = "",
    val format: String = "",
)
