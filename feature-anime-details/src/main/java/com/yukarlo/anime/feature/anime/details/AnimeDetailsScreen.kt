package com.yukarlo.anime.feature.anime.details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.yukarlo.anime.common.android.components.AnimeWithTextOverlay
import com.yukarlo.anime.common.android.components.ScreenState
import com.yukarlo.anime.core.model.AnimeDetails
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
    onUp: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
        ) {
            Box {
                AnimeWithTextOverlay(anime = animeDetails.basicInfo)
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
            if (animeDetails.characters.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(state = rememberScrollState())
                ) {
                    animeDetails.characters.forEach {
                        Column(
                            modifier = Modifier.padding(
                                top = 12.dp,
                                bottom = 12.dp
                            )
                        ) {
                            Text(text = it.name)
                            Text(text = it.voiceActor)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 64.dp))
        }
    }
}

