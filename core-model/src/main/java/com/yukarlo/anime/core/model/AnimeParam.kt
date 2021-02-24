package com.yukarlo.anime.core.model

data class AnimeParam(
    val page: Int = 1,
    val itemsPerPage: Int = 21,
    val sort: List<AnimeSort> = emptyList(),
    val year: Int? = null,
    val season: AnimeSeason? = null
)
