package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ToolBar(
    title: String = "",
    colors: TopAppBarColors = TopAppBarDefaults.smallTopAppBarColors(),
    onUp: () -> Unit
) {
    SmallTopAppBar(
        modifier = Modifier.systemBarsPadding(),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onUp() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        colors = colors
    )
}
