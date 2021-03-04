package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.core.model.Anime

@Composable
fun AnimeRowList(items: List<Anime>, onAnimeClick: (Int?) -> Unit) {
    LazyRow {
        itemsIndexed(items = items) { index, it ->
            val modifier = when (index) {
                0 -> {
                    Modifier.padding(start = 8.dp)
                }
                items.lastIndex -> {
                    Modifier.padding(end = 8.dp)
                }
                else -> {
                    Modifier
                }
            }

            AnimeCard(
                anime = it,
                modifier = modifier,
                onClick = {
                    onAnimeClick(it)
                }
            )
        }
    }
}
