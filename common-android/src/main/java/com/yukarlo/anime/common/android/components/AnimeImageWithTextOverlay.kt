package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.core.model.Anime
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AnimeWithTextOverlay(anime: Anime) {
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(value = 0f) }
    val height = remember { mutableStateOf(value = 0f) }

    Box {
        CoilImage(
            data = anime.coverImage.extraLarge,
            modifier = Modifier
                .requiredHeight(height = 450.dp)
                .onGloballyPositioned {
                    width.value = it.size.width / density
                    height.value = it.size.height / (density * 2)
                },
            contentScale = ContentScale.Crop,
            contentDescription = anime.title.english
        )
        Column(
            modifier = Modifier
                .size(width = width.value.dp, height = height.value.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colors.background),
                        startY = 0F,
                        endY = 600F
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
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = anime.genres.orEmpty(),
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