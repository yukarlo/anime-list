package com.yukarlo.anime.lib.anime.mapper

import com.yukarlo.anime.core.model.AnimeSort
import com.yukarlo.type.MediaSort
import javax.inject.Inject

internal class SortMapper @Inject constructor() {

    fun mapSortToData(animeSort: List<AnimeSort>?): List<MediaSort>? =
        animeSort?.map { sort ->
            when (sort) {
                AnimeSort.SCORE_DESC -> {
                    MediaSort.SCORE_DESC
                }
                AnimeSort.POPULARITY_DESC -> {
                    MediaSort.POPULARITY_DESC
                }
                AnimeSort.TRENDING_DESC -> {
                    MediaSort.TRENDING_DESC
                }
            }
        }
}
