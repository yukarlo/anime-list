package com.yukarlo.anime.lib.anime.mapper

import com.yukarlo.anime.core.model.AnimeSeason
import com.yukarlo.type.MediaSeason
import javax.inject.Inject

internal class SeasonMapper @Inject constructor() {

    fun mapSeasonToData(animeSeason: AnimeSeason?): MediaSeason? =
        when (animeSeason) {
            AnimeSeason.WINTER -> {
                MediaSeason.WINTER
            }
            AnimeSeason.SPRING -> {
                MediaSeason.SPRING
            }
            AnimeSeason.SUMMER -> {
                MediaSeason.SUMMER
            }
            AnimeSeason.FALL -> {
                MediaSeason.FALL
            }
            else -> {
                null
            }
        }
}
