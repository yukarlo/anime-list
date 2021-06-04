package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.yukarlo.anime.core.model.Anime

@Composable
fun AnimeWithTextOverlay(
    anime: Anime,
    textAlign: TextAlign,
    modifier: Modifier
) {
    val density = LocalDensity.current.density
    val height = remember { mutableStateOf(value = 0f) }

    Box {
        Image(
            painter = rememberCoilPainter(request = anime.coverImage.extraLarge),
            modifier = Modifier
                .requiredHeight(height = 450.dp)
                .scrim(colors = listOf(Color(0x80000000), Color(0x33000000)))
                .onGloballyPositioned {
                    height.value = it.size.height / (density * 2)
                },
            contentScale = ContentScale.Crop,
            contentDescription = anime.title.english
        )
        Column(
            modifier = Modifier
                .height(height = height.value.dp)
                .fillMaxWidth()
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
                text = anime.title.userPreferred,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h3,
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = textAlign
            )
            Text(
                text = anime.genres.orEmpty(),
                maxLines = 1,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption,
                modifier = modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                textAlign = textAlign
            )
        }
    }
}

fun Modifier.scrim(colors: List<Color>): Modifier = drawWithContent {
    drawContent()
    drawRect(Brush.verticalGradient(colors))
}
