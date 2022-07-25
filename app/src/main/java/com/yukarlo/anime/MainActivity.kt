package com.yukarlo.anime

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.yukarlo.anime.common.android.navigation.LocalBackDispatcher
import com.yukarlo.anime.common.android.ui.theme.AnimeTheme
import com.yukarlo.anime.navigation.TopLevelNavigationConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets {
                AnimeTheme {
                    MainApp(onBackPressedDispatcher)
                }
            }
        }
    }
}

@Composable
fun MainApp(backDispatcher: OnBackPressedDispatcher) {
    CompositionLocalProvider(LocalBackDispatcher provides backDispatcher) {
        TopLevelNavigationConfig(navController = rememberNavController())
    }
}
