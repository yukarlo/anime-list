package com.yukarlo.anime.common.android.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.core.model.Anime

@Composable
fun VerticalGrid(
    items: List<Anime> = listOf(),
    requestNextPage: () -> Unit,
    dispose: () -> Unit,
    onAnimeClick: (Int?) -> Unit
) {
    val animatedItemIndex = remember { mutableSetOf<Int>() }
    val transition = updateTransition(targetState = animatedItemIndex)

    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 3),
        modifier = Modifier.padding(
            start = 4.dp,
            end = 4.dp
        )
    ) {
        itemsIndexed(items) { index: Int, item: Anime ->
            DisposableEffect(Unit) {
                if (index == items.lastIndex) {
                    requestNextPage()
                }
                onDispose {
                    dispose()
                }
            }
            val offset: Float by transition.animateFloat(
                transitionSpec = {
                    tween(
                        durationMillis = 400,
                        delayMillis = 100,
                        easing = LinearOutSlowInEasing
                    )
                }
            ) { state ->
                if (state.contains(index)) {
                    0F
                } else {
                    150F
                }
            }

            val alpha: Float by transition.animateFloat(
                transitionSpec = {
                    tween(
                        durationMillis = 400,
                        delayMillis = 100,
                        easing = LinearOutSlowInEasing
                    )
                }
            ) { state ->
                if (index in state) {
                    1F
                } else {
                    0F
                }
            }

            animatedItemIndex.add(index)

            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .offset(y = offset.dp)
                    .alpha(alpha = alpha)
            ) {
                AnimeCard(
                    anime = item,
                    onClick = {
                        onAnimeClick(it)
                    },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun <T> LazyGrid(
    items: List<T> = listOf(),
    rows: Int = 2,
    padding: Int = 4,
    headerText: String? = null,
    viewAll: () -> Unit,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val animatedItemIndex = remember { mutableSetOf<Int>() }
    val transition = updateTransition(animatedItemIndex)
    val chunkedList = items.chunked(size = rows)
    LazyColumn {
        headerText?.let {
            item {
                ListHeaderTitle(
                    title = it,
                    viewAll = { viewAll() }
                )
            }
        }
        itemsIndexed(items = chunkedList) { index, it ->
            val offset: Float by transition.animateFloat(
                transitionSpec = {
                    tween(
                        durationMillis = 400,
                        delayMillis = 100,
                        easing = LinearOutSlowInEasing
                    )
                }
            ) { state ->
                if (state.contains(index)) {
                    0F
                } else {
                    150F
                }
            }

            val alpha: Float by transition.animateFloat(
                transitionSpec = {
                    tween(
                        durationMillis = 800,
                        delayMillis = 100,
                        easing = LinearOutSlowInEasing
                    )
                }
            ) { state ->
                if (index in state) {
                    1F
                } else {
                    0F
                }
            }

            animatedItemIndex.add(index)

            Row(
                modifier = Modifier
                    .padding(
                        top = (padding + 8).dp,
                        start = (padding + 4).dp,
                        end = (padding + 4).dp
                    )
                    .offset(y = offset.dp)
                    .alpha(alpha = alpha)
            ) {
                it.forEachIndexed { rowIndex, item ->
                    Box(
                        modifier = Modifier
                            .weight(weight = 1F)
                            .align(alignment = Alignment.Top),
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(item, index * rows + rowIndex)
                    }
                }
            }
        }
    }
}