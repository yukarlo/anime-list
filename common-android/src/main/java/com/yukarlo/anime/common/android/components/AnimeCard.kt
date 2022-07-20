package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
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
            elevation = CardDefaults.cardElevation(
                0.dp,
                0.dp,
                0.dp,
                0.dp,
                0.dp
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(ratio = 3 / 4F)
                    .clickable {
                        onClick(anime.id)
                    },
                painter = rememberImagePainter(
                    data = anime.coverImage.large,
                    builder = {
                        crossfade(true)
                    }),
                contentScale = ContentScale.Crop,
                contentDescription = anime.title.english
            )
        }
        Text(
            text = anime.title.userPreferred,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 2.dp)
                .wrapContentHeight()
        )
        Text(
            maxLines = 1,
            text = anime.title.native,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                .wrapContentHeight()
        )
        Text(
            maxLines = 1,
            text = "${anime.format} • ${anime.startDate?.year}",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                .fillMaxHeight()
        )
    }
}
