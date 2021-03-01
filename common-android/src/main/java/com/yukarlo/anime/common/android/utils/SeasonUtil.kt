package com.yukarlo.anime.common.android.utils

import com.yukarlo.anime.core.model.AnimeSeason
import java.util.*
import javax.inject.Inject

class SeasonUtil @Inject constructor() {

    private val calendar = Calendar.getInstance()

    fun getYear() = calendar.get(Calendar.YEAR)

    fun getAstronomicalSeason(): AnimeSeason =
        when (calendar.get(Calendar.MONTH)) {
            0, 1, 2 -> {
                AnimeSeason.WINTER
            }
            3, 4, 5 -> {
                AnimeSeason.SPRING
            }
            6, 7, 8 -> {
                AnimeSeason.SUMMER
            }
            else -> {
                AnimeSeason.FALL
            }
        }
}
