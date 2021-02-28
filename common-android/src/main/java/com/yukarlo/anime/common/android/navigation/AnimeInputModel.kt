package com.yukarlo.anime.common.android.navigation

import android.os.Parcelable
import com.yukarlo.anime.core.model.AnimeSeason
import com.yukarlo.anime.core.model.AnimeSortTypes
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class AnimeInputModel(
    val sort: @RawValue AnimeSortTypes,
    val year: Int?,
    val season: AnimeSeason?
) : Parcelable
