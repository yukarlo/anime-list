package com.yukarlo.anime.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class AnimeSortTypes(
    val title: String,
    val sortRequest: List<AnimeSort>
) : Parcelable {
    @Parcelize
    object TrendingAnime : AnimeSortTypes(
        title = "Trending this Season",
        sortRequest = listOf(
            AnimeSort.TRENDING_DESC,
            AnimeSort.POPULARITY_DESC
        )
    )

    @Parcelize
    object AllTimePopular : AnimeSortTypes(
        title = "All Time Popular",
        sortRequest = listOf(AnimeSort.SCORE_DESC)
    )
}
