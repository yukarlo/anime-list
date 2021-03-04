package com.yukarlo.anime.common.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.core.model.Character
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .width(width = 140.dp)
            .wrapContentHeight()
    ) {
        Card(
            elevation = 0.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            CoilImage(
                data = character.image.large,
                modifier = Modifier
                    .aspectRatio(3 / 4F),
                contentScale = ContentScale.Crop,
                contentDescription = character.name
            )
        }
        Text(
            text = character.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 2.dp)
                .wrapContentHeight()
        )
    }
}
