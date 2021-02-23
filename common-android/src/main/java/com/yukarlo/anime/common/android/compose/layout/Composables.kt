package com.yukarlo.anime.common.android.compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.core.model.Anime
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun <T> LazyGrid(
    items: List<T> = listOf(),
    rows: Int = 2,
    padding: Int = 4,
    headerText: String? = null,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val chunkedList = items.chunked(size = rows)
    LazyColumn(
        modifier = Modifier.padding(horizontal = padding.dp)
    ) {
        headerText?.let {
            item {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                    style = MaterialTheme.typography.h3
                )
            }
        }
        itemsIndexed(items = chunkedList) { index, it ->
            if (index == 0) {
                Spacer(
                    modifier = Modifier.size(size = padding.dp)
                )
            }

            Row(modifier = Modifier.wrapContentWidth()) {
                it.forEachIndexed { rowIndex, item ->
                    Box(
                        modifier = Modifier
                            .weight(weight = 1F)
                            .align(alignment = Alignment.Top)
                            .padding(vertical = padding.dp),
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
    Card(
        elevation = 0.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Column {
            CoilImage(
                data = anime.coverImage,
                modifier = Modifier
                    .aspectRatio(3 / 4F),
                contentScale = ContentScale.Crop,
                contentDescription = anime.title.english
            )
            Text(
                text = anime.title.english,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 0.dp, start = 4.dp, end = 4.dp)
            )
            Text(
                text = anime.status,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 0.dp, bottom = 4.dp, start = 4.dp, end = 4.dp)
            )
        }
    }
}

@Composable
fun AnimeCardWithTextOverlay(anime: Anime) {
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(value = 0f) }
    val height = remember { mutableStateOf(value = 0f) }

    Box {
        Card(
            elevation = 0.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Box {
                CoilImage(
                    data = anime.coverImage,
                    modifier = Modifier
                        .aspectRatio(3 / 4F)
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
                                colors = listOf(Color.Transparent, MaterialTheme.colors.surface),
                                startY = 588F,
                                endY = 888F
                            )
                        )
                        .align(alignment = Alignment.BottomStart)
                ) { }
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .padding(start = 4.dp, end = 4.dp)
                ) {
                    Text(
                        text = anime.title.english,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                    Text(
                        text = anime.status,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = 0.dp, bottom = 4.dp)
                    )
                }
            }
        }
    }
}