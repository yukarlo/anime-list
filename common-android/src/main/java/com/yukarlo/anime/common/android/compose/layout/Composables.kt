package com.yukarlo.anime.common.android.compose.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.common.android.base.Result
import com.yukarlo.anime.common.android.base.Result.LOADING
import com.yukarlo.anime.common.android.ui.theme.teal200
import com.yukarlo.anime.core.model.Anime
import dev.chrisbanes.accompanist.coil.CoilImage
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

@Composable
fun AnimeCard(anime: Anime) {
    Column {
        Card(
            elevation = 0.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            CoilImage(
                data = anime.coverImage.large,
                modifier = Modifier
                    .aspectRatio(3 / 4F),
                contentScale = ContentScale.Crop,
                contentDescription = anime.title.english
            )
        }
        Text(
            text = anime.title.english,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp)
        )
        Text(
            maxLines = 1,
            text = anime.status,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(bottom = 4.dp, start = 4.dp, end = 4.dp)
        )
    }
}

@Composable
fun AnimeWithTextOverlay(anime: Anime) {
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(value = 0f) }
    val height = remember { mutableStateOf(value = 0f) }

    Box {
        CoilImage(
            data = anime.coverImage.extraLarge,
            modifier = Modifier
                .preferredHeight(350.dp)
                .onGloballyPositioned {
                    width.value = it.size.width / density
                    height.value = it.size.height / density
                },
            contentScale = ContentScale.Crop,
            contentDescription = anime.title.english
        )
        Column(
            modifier = Modifier
                .size(width.value.dp, height.value.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colors.background),
                        startY = 150F,
                        endY = 1000F
                    )
                )
                .align(alignment = Alignment.BottomStart)
        ) { }
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(start = 4.dp, end = 4.dp, bottom = 16.dp)
        ) {
            Text(
                text = anime.title.english,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = anime.status,
                maxLines = 1,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ScreenState(
    result: Result,
    renderView: @Composable () -> Unit
) {
    when (result) {
        Result.ERROR -> {
            ErrorView()
        }
        Result.SUCCESS -> {
            renderView()
        }
        LOADING -> {
            LoadingView()
        }
    }

}

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

@Composable
fun ErrorView(message: String = "Oops! Something went wrong!") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = MaterialTheme.colors.error)
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = teal200,
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
        )
    }
}