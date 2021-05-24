package com.yukarlo.anime.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class AnimeSortTypes(
    val title: String,
    val sortRequest: List<AnimeSort>
) : Parcelable {
    @Parcelize
    object TrendingAnime : AnimeSortTypes(
        title = "Trending Now",
        sortRequest = listOf(
            AnimeSort.TRENDING_DESC
        )
    )

    @Parcelize
    object AllTimePopular : AnimeSortTypes(
        title = "All Time Popular",
        sortRequest = listOf(AnimeSort.POPULARITY_DESC)
    )

    @Parcelize
    object PopularThisSeason : AnimeSortTypes(
        title = "Popular This Season",
        sortRequest = listOf(AnimeSort.POPULARITY_DESC)
    )

    @Parcelize
    object Top10 : AnimeSortTypes(
        title = "Top 10 Anime",
        sortRequest = listOf(AnimeSort.SCORE_DESC)
    )
}
