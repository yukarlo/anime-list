package com.yukarlo.anime.common.android.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun ToolBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}