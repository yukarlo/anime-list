package com.yukarlo.anime

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.yukarlo.anime.common.android.ui.theme.AnimeTheme
import com.yukarlo.anime.core.navigation.ComposeNavigationFactory
import com.yukarlo.anime.feature.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var composeNavigationFactories: @JvmSuppressWildcards Set<ComposeNavigationFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets {
                AnimeTheme {
                    MainApp(composeNavigationFactories)
                }
            }
        }
    }
}

@Composable
fun MainApp(composeNavigationFactories: @JvmSuppressWildcards Set<ComposeNavigationFactory>) {
    MainScreen(
        navController = rememberNavController(),
        composeNavigationFactories = composeNavigationFactories
    )
}
