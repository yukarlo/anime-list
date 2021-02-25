package com.yukarlo.anime.common.android.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun <T> LazyGrid(
    items: List<T> = listOf(),
    rows: Int = 2,
    padding: Int = 4,
    headerText: String? = null,
    viewAll: () -> Unit,
    banner: @Composable LazyItemScope.() -> Unit,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val animatedSet = remember { mutableSetOf<Int>() }
    val chunkedList = items.chunked(size = rows)
    LazyColumn {
        item {
            banner()
        }
        headerText?.let {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .padding(
                                start = (padding + 8).dp,
                                end = (padding + 2).dp,
                                top = padding.dp
                            ),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = "view all",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterEnd)
                            .padding(
                                start = (padding + 2).dp,
                                end = (padding + 8).dp,
                                top = padding.dp
                            )
                            .clickable {
                                viewAll()
                            },
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
        itemsIndexed(items = chunkedList) { index, it ->
            val offsetValue = remember {
                Animatable(
                    initialValue = if (index in animatedSet) {
                        0F
                    } else {
                        150F
                    }
                )
            }

            val alphaValue = remember {
                Animatable(
                    initialValue = if (index in animatedSet) {
                        1F
                    } else {
                        150F
                    }
                )
            }

            val composableScope = rememberCoroutineScope()
            DisposableEffect(Unit) {
                composableScope.launch {
                    offsetValue.animateTo(
                        targetValue = 0F,
                        animationSpec = TweenSpec(
                            durationMillis = 400,
                            delay = 100,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    alphaValue.animateTo(
                        targetValue = 1F,
                        animationSpec = TweenSpec(
                            durationMillis = 400,
                            delay = 100,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    animatedSet.add(index)
                }

                onDispose {
                    composableScope.launch {
                        offsetValue.snapTo(0F)
                        offsetValue.stop()
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = padding.dp,
                        start = (padding + 4).dp,
                        end = (padding + 4).dp,
                        bottom = (padding + 8).dp
                    )
                    .offset(y = offsetValue.value.toInt().dp)
                    .alpha(alpha = alphaValue.value)
                    .wrapContentWidth()
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