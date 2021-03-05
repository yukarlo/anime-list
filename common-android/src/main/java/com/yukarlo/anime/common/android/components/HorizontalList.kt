package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> HorizontalList(
    items: List<T>,
    itemContent: @Composable LazyItemScope.(T, Modifier) -> Unit
) {
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

            itemContent(it, modifier)
        }
    }
}
