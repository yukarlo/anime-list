package com.yukarlo.anime.core.model

import android.os.Parcelable
import com.yukarlo.anime.core.model.AnimeSeason
import com.yukarlo.anime.core.model.AnimeSortTypes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeInputModel(
    val sort: AnimeSortTypes,
    val year: Int? = null,
    val season: AnimeSeason? = null
) : Parcelable
