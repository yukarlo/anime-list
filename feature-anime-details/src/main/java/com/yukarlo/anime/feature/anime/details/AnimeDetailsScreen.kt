package com.yukarlo.anime.feature.anime.details

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.yukarlo.anime.common.android.components.ListHeaderTitle
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.common.android.ui.theme.DynamicThemePrimaryColorsFromImage
import com.yukarlo.anime.common.android.ui.theme.MinContrastOfPrimaryVsSurface
import com.yukarlo.anime.common.android.ui.theme.contrastAgainst
import com.yukarlo.anime.common.android.ui.theme.rememberDominantColorState
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.feature.anime.details.components.*

@Composable
internal fun AnimeDetailsScreen(
    viewModel: AnimeDetailsViewModel,
    navController: NavHostController,
    openDetails: (Int) -> Unit
) {
    val animeDetailsScreenState by viewModel.onAnimeDetails.collectAsState()

    BackHandler {
        navController.popBackStack()
    }

    ScreenState(result = animeDetailsScreenState.result,
        renderView = {
            AnimeDetails(
                animeDetails = animeDetailsScreenState.animeDetails,
                navController = navController,
                onAnimeClick = openDetails
            )
        },
        retry = { }
    )
}

@Composable
private fun AnimeDetails(
    animeDetails: AnimeDetails,
    navController: NavHostController,
    onAnimeClick: (Int) -> Unit
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val dominantColorState = rememberDominantColorState { color ->
        color.contrastAgainst(surfaceColor) >= MinContrastOfPrimaryVsSurface
    }

    DynamicThemePrimaryColorsFromImage(dominantColorState) {
        val selectedImageUrl = animeDetails.basicInfo.coverImage.extraLarge

        LaunchedEffect(selectedImageUrl) {
            dominantColorState.updateColorsFromImageUrl(selectedImageUrl)
        }

        val context = LocalContext.current
        val chunkedList = animeDetails.recommendations.chunked(size = 2)
        Scaffold { innerPadding ->
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
                item {
                    HeaderSection(anime = animeDetails.basicInfo, onUp = { navController.popBackStack() })

                    SmallInformationSection(animeDetails = animeDetails)

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    DescriptionSection(description = animeDetails.description)

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    TrailerSection(trailer = animeDetails.trailer) {
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(it)
                            startActivity(context, this, null)
                        }
                    }

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    CharactersRowSection(characters = animeDetails.characters)

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    MoreInformationSection(animeDetails = animeDetails)

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    ListHeaderTitle(
                        title = "Some Recommendations",
                        viewAll = { },
                        showViewAll = false
                    )
                }

                items(chunkedList) { item ->
                    RecommendationGridSection(
                        anime = item,
                        onAnimeClick = { animeId ->
                            animeId?.let {
                                onAnimeClick(it)
                            }
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.padding(top = 64.dp))
                }
            }
        }
    }
}
