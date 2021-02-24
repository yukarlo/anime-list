package com.yukarlo.feature.anime.home

import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.core.model.Anime

internal data class HomeUiState(
    val result: Result = Result.LOADING,
    val homeItems: List<Anime> = listOf(),
    val homeAnimeBanner: Anime? = null
)
