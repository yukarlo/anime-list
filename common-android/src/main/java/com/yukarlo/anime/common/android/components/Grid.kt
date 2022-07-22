package com.yukarlo.anime.common.android.components

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T : Any> VerticalGrid(
    modifier: Modifier,
    items: List<T> = listOf(),
    itemContent: @Composable LazyGridItemScope.(T, Int) -> Unit
) {
    val animatedItemIndex = remember { mutableSetOf<Int>() }
    val transition = updateTransition(targetState = animatedItemIndex, label = "")

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        modifier = modifier.padding(
            start = 4.dp,
            end = 4.dp
        )
    ) {
        itemsIndexed(items) { index, item ->
//            val offset: Float by transition.animateFloat(
//                transitionSpec = {
//                    tween(
//                        durationMillis = 400,
//                        delayMillis = 100,
//                        easing = LinearOutSlowInEasing
//                    )
//                }, label = ""
//            ) { state ->
//                if (state.contains(index)) {
//                    0F
//                } else {
//                    150F
//                }
//            }
//
//            val alpha: Float by transition.animateFloat(
//                transitionSpec = {
//                    tween(
//                        durationMillis = 400,
//                        delayMillis = 100,
//                        easing = LinearOutSlowInEasing
//                    )
//                }, label = ""
//            ) { state ->
//                if (index in state) {
//                    1F
//                } else {
//                    0F
//                }
//            }

            animatedItemIndex.add(index)

            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
//                    .offset(y = offset.dp)
//                    .alpha(alpha = alpha)
            ) {
                itemContent(item, index)
            }
        }
    }
}
