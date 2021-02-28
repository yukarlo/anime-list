package com.yukarlo.anime.core.model

sealed class AnimeRequest(
    val title: String,
    val sortRequest: List<AnimeSort>
) {
    object TrendingAnime : AnimeRequest(
        title = "Trending this Season",
        sortRequest = listOf(
            AnimeSort.TRENDING_DESC,
            AnimeSort.POPULARITY_DESC
        )
    )

    object AllTimePopular : AnimeRequest(
        title = "All Time Popular",
        sortRequest = listOf(AnimeSort.SCORE_DESC)
    )
}
