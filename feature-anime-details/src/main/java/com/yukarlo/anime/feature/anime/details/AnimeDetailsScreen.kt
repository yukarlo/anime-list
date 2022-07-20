package com.yukarlo.anime.feature.anime.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.yukarlo.anime.common.android.components.ListHeaderTitle
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.core.model.AnimeDetails
import com.yukarlo.anime.feature.anime.details.components.*

@Composable
fun AnimeDetailsScreen(
    navigateUp: () -> Unit,
    openDetails: (Int) -> Unit
) {
    val viewModel: AnimeDetailsViewModel = hiltViewModel()

    val animeDetailsScreenState by viewModel.onAnimeDetails.collectAsState()
    ScreenState(result = animeDetailsScreenState.result,
        renderView = {
            AnimeDetails(
                animeDetails = animeDetailsScreenState.animeDetails,
                onUp = navigateUp,
                onAnimeClick = openDetails
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
    Box (modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
