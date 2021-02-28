package com.yukarlo.feature.anime.home

import com.yukarlo.anime.common.android.base.Result

internal data class HomeUiState(
    val result: Result = Result.LOADING,
    val homeItems: List<HomeItems> = emptyList()
)
