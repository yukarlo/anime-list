package com.yukarlo.anime.feature.anime.details

import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.core.model.AnimeDetails

internal data class AnimeDetailsUiState(
    val result: Result = Result.LOADING,
    val animeDetails: AnimeDetails = AnimeDetails()
)
