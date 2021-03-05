package com.yukarlo.anime.common.android.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ToolBar(
    title: String,
    onUp: () -> Unit
) {
    TopAppBar(
        title = {
            IconButton(onClick = {
                onUp()
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "back"
                )
            }

            Text(
                text = title,
            )
        },
        elevation = 12.dp
    )
}
