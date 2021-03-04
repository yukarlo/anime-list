package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    label: String,
    icon: ImageVector? = null,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.secondaryVariant
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "icon",
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 4.dp)
                    )
                }
                Text(
                    label,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}