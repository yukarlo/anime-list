package com.yukarlo.anime.feature.anime.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.core.text.HtmlCompat
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.systemBarsPadding
import com.yukarlo.anime.common.android.components.*
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.Character
import com.yukarlo.anime.core.model.Trailer
import com.yukarlo.anime.feature.anime.details.R
import java.util.Locale

@Composable
fun HeaderSection(
    anime: Anime,
    onUp: () -> Unit
) {
    Box {
        AnimeWithTextOverlay(
            anime = anime,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        ToolBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
        ) { onUp() }
    }
}

@Composable
fun SmallInformationSection(animeDetails: AnimeDetails) {
    Row {
        Chip(
            label = "Studio: ${animeDetails.studio}",
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 4.dp
                )
        )
        Chip(
            label = animeDetails.basicInfo.startDate?.year.orEmpty(),
            modifier = Modifier
                .padding(
                    start = 4.dp,
                    end = 4.dp
                )
        )
        Chip(
            label = "${animeDetails.episodes} episodes",
            modifier = Modifier
                .padding(
                    start = 4.dp,
                    end = 4.dp
                )
        )
        Chip(
            label = "${animeDetails.duration} mins",
            modifier = Modifier
                .padding(
                    start = 4.dp,
                    end = 12.dp
                )
        )
    }
}

@Composable
fun DescriptionSection(description: String) {
    if (description.isNotEmpty()) {
        Text(
            text = HtmlCompat.fromHtml(description, 0).toString(),
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    start = 12.dp,
                    end = 12.dp
                ),
            textAlign = TextAlign.Start,
            maxLines = 6,
            lineHeight = 24.sp,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun TrailerSection(
    trailer: Trailer,
    playTrailer: (String) -> Unit
) {
    ListHeaderTitle(
        title = "Trailer",
        viewAll = { },
        showViewAll = false
    )

    if (trailer.youtubeTrailerUrl.isNotEmpty()) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable {
                    playTrailer(trailer.youtubeTrailerUrl)
                }
        ) {
            Image(
                painter = rememberImagePainter(data = trailer.youtubeThumbnail),
                modifier = Modifier
                    .aspectRatio(ratio = 16 / 9F)
                    .scrim(colors = listOf(Color(0x80000000), Color(0x33000000))),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_youtube),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .height(64.dp),
                contentDescription = "play button"
            )
        }
    } else {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            text = "No trailer available",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun CharactersRowSection(characters: List<Character>) {
    if (characters.isNotEmpty()) {
        ListHeaderTitle(
            title = "Characters",
            viewAll = { },
            showViewAll = false
        )

        LazyRow {
            itemsIndexed(items = characters) { index, it ->
                val modifier = when (index) {
                    0 -> {
                        Modifier.padding(start = 8.dp)
                    }
                    characters.lastIndex -> {
                        Modifier.padding(end = 8.dp)
                    }
                    else -> {
                        Modifier
                    }
                }

                CharacterCard(
                    character = it,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun RecommendationGridSection(anime: List<Anime>, onAnimeClick: (Int?) -> Unit) {
    if (anime.isNotEmpty()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(height = 350.dp)
        ) {
            anime.forEach { item ->
                Box(
                    modifier = Modifier
                        .weight(weight = 1F)
                        .align(alignment = Alignment.Top),
                    contentAlignment = Alignment.Center
                ) {
                    AnimeCard(
                        anime = item,
                        onClick = {
                            onAnimeClick(it)
                        },
                        modifier = Modifier
                    )
                }
            }
            repeat(times = 2 - anime.size) {
                Box(
                    modifier = Modifier
                        .weight(weight = 1F)
                        .align(alignment = Alignment.Top),
                    contentAlignment = Alignment.Center
                ) {}
            }
        }
    }
}

@Composable
fun MoreInformationSection(animeDetails: AnimeDetails) {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val constraints = decoupledConstraints(
            haveNextAiringSchedule = animeDetails.nextAiringSchedule != null
        )

        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 36.dp,
                    vertical = 12.dp
                )
        ) {
            Text(
                text = "More Information",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .layoutId("moreInformationLabel")
                    .padding(bottom = 12.dp)
            )

            Text(
                text = "English Title:",
                modifier = Modifier
                    .layoutId("titleEnglishLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = animeDetails.basicInfo.title.userPreferred,
                modifier = Modifier
                    .layoutId("titleEnglish")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = "Native Title:",
                modifier = Modifier
                    .layoutId("titleNativeLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = animeDetails.basicInfo.title.native,
                modifier = Modifier
                    .layoutId("titleNative")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = "Average Score:",
                modifier = Modifier
                    .layoutId("avgScoreLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = animeDetails.basicInfo.averageScore.toString(),
                modifier = Modifier
                    .layoutId("avgScore")
                    .padding(
                        bottom = 8.dp
                    )
            )

            Text(
                text = "Premier Date:",
                modifier = Modifier
                    .layoutId("premierLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = animeDetails.basicInfo.startDate?.let {
                    "${it.month}.${it.day}.${it.year}"
                } ?: "N/A",
                modifier = Modifier
                    .layoutId("premier")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = "End Date:",
                modifier = Modifier
                    .layoutId("endDateLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = animeDetails.basicInfo.endDate?.let {
                    "${it.month}.${it.day}.${it.year}"
                } ?: "TBA",
                modifier = Modifier
                    .layoutId("endDate")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = "Anime Status:",
                modifier = Modifier
                    .layoutId("statusLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = animeDetails.basicInfo.status.lowercase().replaceFirstChar {
                    if (it.isLowerCase()) {
                        it.titlecase(Locale.getDefault())
                    } else {
                        it.toString()
                    }
                },
                modifier = Modifier
                    .layoutId("status")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            animeDetails.nextAiringSchedule?.let {
                Text(
                    text = "Airing:",
                    modifier = Modifier
                        .layoutId("nextAiringScheduleLabel")
                        .padding(
                            bottom = 8.dp
                        ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Ep ${it.episodeNumber} in ${it.date}",
                    modifier = Modifier
                        .layoutId("nextAiringSchedule")
                        .padding(
                            bottom = 8.dp
                        ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}

private fun decoupledConstraints(haveNextAiringSchedule: Boolean): ConstraintSet =
    ConstraintSet {
        val moreInformationLabel = createRefFor("moreInformationLabel")

        val titleEnglishLabel = createRefFor("titleEnglishLabel")
        val titleEnglish = createRefFor("titleEnglish")

        val titleNativeLabel = createRefFor("titleNativeLabel")
        val titleNative = createRefFor("titleNative")

        val avgScoreLabel = createRefFor("avgScoreLabel")
        val avgScore = createRefFor("avgScore")

        val premierLabel = createRefFor("premierLabel")
        val premier = createRefFor("premier")

        val endDateLabel = createRefFor("endDateLabel")
        val endDate = createRefFor("endDate")

        val statusLabel = createRefFor("statusLabel")
        val status = createRefFor("status")

        val nextAiringScheduleLabel = createRefFor("nextAiringScheduleLabel")
        val nextAiringSchedule = createRefFor("nextAiringSchedule")

        constrain(ref = moreInformationLabel) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(titleEnglishLabel.top)
        }

        constrain(ref = titleEnglishLabel) {
            top.linkTo(moreInformationLabel.bottom)
            start.linkTo(parent.start)
            end.linkTo(titleEnglish.start)
            width = Dimension.percent(0.35F)
        }
        constrain(ref = titleEnglish) {
            top.linkTo(titleEnglishLabel.top)
            end.linkTo(parent.end)
            start.linkTo(titleEnglishLabel.end)
            bottom.linkTo(titleNative.top)
            width = Dimension.fillToConstraints
        }

        constrain(ref = titleNativeLabel) {
            top.linkTo(titleEnglish.bottom)
            start.linkTo(parent.start)
            end.linkTo(titleNative.start)
            width = Dimension.percent(0.35F)
        }
        constrain(ref = titleNative) {
            top.linkTo(titleNativeLabel.top)
            start.linkTo(titleNativeLabel.end)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(ref = avgScoreLabel) {
            top.linkTo(titleNative.bottom)
            start.linkTo(parent.start)
            end.linkTo(avgScore.start)
            width = Dimension.percent(0.35F)
        }
        constrain(ref = avgScore) {
            top.linkTo(avgScoreLabel.top)
            start.linkTo(avgScoreLabel.end)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(ref = premierLabel) {
            top.linkTo(avgScoreLabel.bottom)
            start.linkTo(parent.start)
            end.linkTo(premier.start)
            width = Dimension.percent(0.35F)
        }
        constrain(ref = premier) {
            top.linkTo(premierLabel.top)
            start.linkTo(premierLabel.end)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(ref = endDateLabel) {
            top.linkTo(premierLabel.bottom)
            start.linkTo(parent.start)
            end.linkTo(endDate.start)
            width = Dimension.percent(0.35F)
        }
        constrain(ref = endDate) {
            top.linkTo(endDateLabel.top)
            start.linkTo(endDateLabel.end)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(ref = statusLabel) {
            top.linkTo(endDateLabel.bottom)
            start.linkTo(parent.start)
            end.linkTo(status.start)
            width = Dimension.percent(0.35F)
        }
        constrain(ref = status) {
            top.linkTo(statusLabel.top)
            start.linkTo(statusLabel.end)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        if (haveNextAiringSchedule) {
            constrain(ref = nextAiringScheduleLabel) {
                top.linkTo(statusLabel.bottom)
                start.linkTo(parent.start)
                end.linkTo(nextAiringSchedule.start)
                width = Dimension.percent(0.35F)
            }
            constrain(ref = nextAiringSchedule) {
                top.linkTo(nextAiringScheduleLabel.top)
                start.linkTo(nextAiringScheduleLabel.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        }
    }
