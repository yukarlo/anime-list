package com.yukarlo.anime.common.android.components

import androidx.compose.runtime.Composable
import com.yukarlo.anime.common.android.base.Result

@Composable
fun ScreenState(
    result: Result,
    renderView: @Composable () -> Unit,
    retry: () -> Unit
) {
    when (result) {
        Result.ERROR -> {
            ErrorView(retry = retry)
        }
        Result.SUCCESS -> {
            renderView()
        }
        Result.LOADING -> {
            CircularLoading()
        }
    }
}