package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.yukarlo.anime.core.model.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .width(width = 140.dp)
            .height(height = 220.dp)
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
                painter = rememberImagePainter(data = character.image.large),
                contentDescription = character.name,
                modifier = Modifier
                    .aspectRatio(3 / 4F),
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            text = character.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 2.dp)
                .wrapContentHeight()
        )
    }
}
