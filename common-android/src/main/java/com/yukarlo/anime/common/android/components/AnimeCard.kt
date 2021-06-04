package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.yukarlo.anime.core.model.Anime

@Composable
fun AnimeCard(
    anime: Anime,
    onClick: (Int?) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .clickable {
                onClick(anime.id)
            }
    ) {
        Card(
            elevation = 0.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Image(
                modifier = Modifier.aspectRatio(3 / 4F),
                painter = rememberCoilPainter(anime.coverImage.large, fadeIn = true),
                contentScale = ContentScale.Crop,
                contentDescription = anime.title.english
            )
        }
        Text(
            text = anime.title.userPreferred,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 2.dp)
                .wrapContentHeight()
        )
        Text(
            maxLines = 1,
            text = anime.title.native,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.overline,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                .wrapContentHeight()
        )
        Text(
            maxLines = 1,
            text = "${anime.format} • ${anime.startDate?.year}",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                .fillMaxHeight()
        )
    }
}
