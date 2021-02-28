package com.yukarlo.anime.common.android.navigation

import android.os.Parcelable
import com.yukarlo.anime.core.model.AnimeSeason
import com.yukarlo.anime.core.model.AnimeSort
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeInputModel(
    val sort: List<AnimeSort>,
    val year: Int?,
    val season: AnimeSeason?
) : Parcelable
