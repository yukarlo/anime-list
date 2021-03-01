package com.yukarlo.feature.anime.home

import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeSortTypes

internal data class HomeUiState(
    val result: Result = Result.LOADING,
    val homeItems: LinkedHashMap<AnimeSortTypes, List<Anime>> = linkedMapOf()
)
