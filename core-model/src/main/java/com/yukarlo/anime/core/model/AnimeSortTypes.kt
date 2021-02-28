package com.yukarlo.anime.core.model

sealed class AnimeSortTypes(
    val title: String,
    val sortRequest: List<AnimeSort>
) {
    object TrendingAnime : AnimeSortTypes(
        title = "Trending this Season",
        sortRequest = listOf(
            AnimeSort.TRENDING_DESC,
            AnimeSort.POPULARITY_DESC
        )
    )

    object AllTimePopular : AnimeSortTypes(
        title = "All Time Popular",
        sortRequest = listOf(AnimeSort.SCORE_DESC)
    )
}
