package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ListHeaderTitle(
    title: String,
    viewAll: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(
                    start = 12.dp,
                    end = 6.dp,
                    top = 8.dp
                ),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.caption
        )
        Text(
            text = "view all",
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .padding(
                    start = 8.dp,
                    end = 12.dp,
                    top = 4.dp
                )
                .clickable {
                    viewAll()
                },
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.caption
        )
    }
}
