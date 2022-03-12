package com.yukarlo.anime.feature.anime.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.yukarlo.anime.common.android.components.ListHeaderTitle
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.feature.anime.details.components.CharactersRowSection
import com.yukarlo.anime.feature.anime.details.components.DescriptionSection
import com.yukarlo.anime.feature.anime.details.components.HeaderSection
import com.yukarlo.anime.feature.anime.details.components.MoreInformationSection
import com.yukarlo.anime.feature.anime.details.components.RecommendationGridSection
import com.yukarlo.anime.feature.anime.details.components.SmallInformationSection
import com.yukarlo.anime.feature.anime.details.components.TrailerSection

@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val parentEntry =
        remember { navController.getBackStackEntry(navBackStackEntry.destination.route ?: "") }
    val viewModel: AnimeDetailsViewModel = hiltViewModel(parentEntry)

    LaunchedEffect(animeId) {
        viewModel.getAnimeDetails(animeId = animeId)
    }

    val animeDetailsScreenState by viewModel.onAnimeDetails.collectAsState()
    ScreenState(result = animeDetailsScreenState.result,
        renderView = {
            AnimeDetails(
                animeDetails = animeDetailsScreenState.animeDetails,
                onUp = navController::navigateUp,
                onAnimeClick = viewModel::getAnimeDetails
            )
        },
        retry = { }
    )
}

@Composable
private fun AnimeDetails(
    animeDetails: AnimeDetails,
    onAnimeClick: (Int) -> Unit,
    onUp: () -> Unit
) {
    val context = LocalContext.current
    val chunkedList = animeDetails.recommendations.chunked(size = 2)
    Box {
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
