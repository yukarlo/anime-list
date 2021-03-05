package com.yukarlo.anime.feature.anime.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import androidx.constraintlayout.compose.Dimension.Companion.percent
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.yukarlo.anime.common.android.components.*
import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.core.model.Character
import dev.chrisbanes.accompanist.insets.systemBarsPadding

@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val factory = HiltViewModelFactory(
        context = LocalContext.current,
        navBackStackEntry = navBackStackEntry
    )
    val viewModel: AnimeDetailsViewModel = viewModel(
        key = AnimeDetailsViewModel::class.java.simpleName,
        factory = factory
    )

    LaunchedEffect(animeId) {
        viewModel.getAnimeDetails(animeId = animeId)
    }

    viewModel.onAnimeDetails.collectAsState().value.let { animeDetailsScreenState ->
        ScreenState(result = animeDetailsScreenState.result,
            renderView = {
                AnimeDetails(
                    animeDetails = animeDetailsScreenState.animeDetails,
                    onUp = {
                        navController.navigateUp()
                    },
                    onAnimeClick = { animeId ->
                        animeId?.let {
                            viewModel.getAnimeDetails(animeId = it)
                        }
                    }
                )
            },
            retry = { }
        )

    }
}

@Composable
private fun AnimeDetails(
    animeDetails: AnimeDetails,
    onAnimeClick: (Int?) -> Unit,
    onUp: () -> Unit
) {
    val chunkedList = animeDetails.recommendations.chunked(size = 2)
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                HeaderSection(anime = animeDetails.basicInfo, onUp = { onUp() })

                SmallInformationSection(animeDetails = animeDetails)

                Spacer(modifier = Modifier.padding(top = 12.dp))

                DescriptionSection(description = animeDetails.description)

                Spacer(modifier = Modifier.padding(top = 12.dp))

                CharactersRowSection(characters = animeDetails.characters)

                Spacer(modifier = Modifier.padding(top = 12.dp))

                MoreInformationSection(anime = animeDetails.basicInfo)

                Spacer(modifier = Modifier.padding(top = 12.dp))

                ListHeaderTitle(
                    title = "Some Recommendations",
                    viewAll = { },
                    showViewAll = false
                )

//                Spacer(modifier = Modifier.padding(top = 12.dp))
//                RelationRowSection(anime = animeDetails.relations, onAnimeClick = { })
            }

            items(chunkedList) { item ->
                RecommendationGridSection(anime = item, onAnimeClick = {
                    onAnimeClick(it)
                })
            }

            item {
                Spacer(modifier = Modifier.padding(top = 64.dp))
            }
        }
    }
}

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
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White,
            modifier = Modifier.systemBarsPadding()
        ) {
            IconButton(onClick = {
                onUp()
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "back"
                )
            }
        }
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
            style = MaterialTheme.typography.h6
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
        }
    }
}

@Composable
fun RelationRowSection(anime: List<Anime>, onAnimeClick: (Int?) -> Unit) {
    if (anime.isNotEmpty()) {
        ListHeaderTitle(
            title = "Relations",
            viewAll = { },
            showViewAll = false
        )

        HorizontalList(
            items = anime
        ) { item: Anime, modifier: Modifier ->
            AnimeCard(
                anime = item,
                modifier = modifier
                    .width(width = 140.dp)
                    .height(height = 260.dp),
                onClick = {
                    onAnimeClick(it)
                }
            )
        }
    }
}

@Composable
fun MoreInformationSection(anime: Anime) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val constraints = decoupledConstraints()

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
                style = MaterialTheme.typography.h6,
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
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = anime.title.userPreferred,
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
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = anime.title.native,
                modifier = Modifier
                    .layoutId("titleNative")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = "Premier:",
                modifier = Modifier
                    .layoutId("premierLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = anime.startDate?.let {
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
                text = "Status:",
                modifier = Modifier
                    .layoutId("statusLabel")
                    .padding(
                        bottom = 8.dp
                    ),
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = anime.status.toLowerCase().capitalize(),
                modifier = Modifier
                    .layoutId("status")
                    .padding(
                        bottom = 8.dp
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val moreInformationLabel = createRefFor("moreInformationLabel")

        val titleEnglishLabel = createRefFor("titleEnglishLabel")
        val titleEnglish = createRefFor("titleEnglish")

        val titleNativeLabel = createRefFor("titleNativeLabel")
        val titleNative = createRefFor("titleNative")

        val premierLabel = createRefFor("premierLabel")
        val premier = createRefFor("premier")

        val statusLabel = createRefFor("statusLabel")
        val status = createRefFor("status")

        constrain(ref = moreInformationLabel) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(titleEnglishLabel.top)
        }

        constrain(ref = titleEnglishLabel) {
            top.linkTo(moreInformationLabel.bottom)
            start.linkTo(parent.start)
            end.linkTo(titleEnglish.start)
            width = percent(0.3F)
        }
        constrain(ref = titleEnglish) {
            top.linkTo(titleEnglishLabel.top)
            end.linkTo(parent.end)
            start.linkTo(titleEnglishLabel.end)
            bottom.linkTo(titleNative.top)
            width = fillToConstraints

        }

        constrain(ref = titleNativeLabel) {
            top.linkTo(titleEnglish.bottom)
            start.linkTo(parent.start)
            end.linkTo(titleNative.start)
            width = percent(0.3F)
        }
        constrain(ref = titleNative) {
            top.linkTo(titleNativeLabel.top)
            start.linkTo(titleNativeLabel.end)
            end.linkTo(parent.end)
            width = fillToConstraints
        }

        constrain(ref = premierLabel) {
            top.linkTo(titleNative.bottom)
            start.linkTo(parent.start)
            end.linkTo(premier.start)
            width = percent(0.3F)
        }
        constrain(ref = premier) {
            top.linkTo(premierLabel.top)
            start.linkTo(premierLabel.end)
            end.linkTo(parent.end)
            width = fillToConstraints
        }

        constrain(ref = statusLabel) {
            top.linkTo(premierLabel.bottom)
            start.linkTo(parent.start)
            end.linkTo(premier.start)
            width = percent(0.3F)
        }
        constrain(ref = status) {
            top.linkTo(statusLabel.top)
            start.linkTo(statusLabel.end)
            end.linkTo(parent.end)
            width = fillToConstraints
        }
    }
}

