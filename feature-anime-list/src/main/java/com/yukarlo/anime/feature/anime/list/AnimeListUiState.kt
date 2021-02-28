package com.yukarlo.anime.feature.anime.list

import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.core.model.Anime

internal data class AnimeListUiState(
    val result: Result = Result.LOADING,
    val toolbarTitle: String = "",
    val animeList: List<Anime> = emptyList()
)
