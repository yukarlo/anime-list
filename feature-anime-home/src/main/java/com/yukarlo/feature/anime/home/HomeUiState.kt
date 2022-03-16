package com.yukarlo.feature.anime.home

import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeSortTypes

internal data class HomeUiState(
    val result: Result = Result.LOADING,
    val trendingAnime: List<Anime> = emptyList(),
    val allTimePopularAnime: List<Anime> = emptyList(),
    val popularThisSeasonAnime: List<Anime> = emptyList(),
    val topTenAnime: List<Anime> = emptyList()
)
