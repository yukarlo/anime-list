package com.yukarlo.anime.core.model

data class Anime(
    val id: Int? = null,
    val title: Title = Title(),
    val coverImage: Image = Image(),
    val genres: String? = null,
    val startDate: String = "",
    val endDate: String = "",
    val status: String = "",
    val formatAndYear: String = "",
)
