package com.yukarlo.anime.common.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = grey900,
    onPrimary = grey50,
    primaryContainer = yellow800,
    onPrimaryContainer = grey800,
    secondary = teal200,
    onSecondary = grey50,
    background = grey1000,
    surface = grey900,
    onBackground = grey50,
    onSurface = grey50
)

private val LightColorScheme = lightColorScheme(
    primary = grey200,
    onPrimary = grey700,
    primaryContainer = yellow800,
    onPrimaryContainer = grey800,
    secondary = teal200,
    onSecondary = grey50,
    background = grey50,
    surface = grey200,
    onBackground = grey900,
    onSurface = grey900
)

@Composable
fun AnimeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    AppTheme(colorScheme = colors, content = content)
}

private val GreyThemeLightColorScheme = lightColorScheme(
    surface = grey200,
    onPrimary = grey800,
)

private val GreyThemeDarkColorScheme = darkColorScheme(
    surface = grey900,
    onPrimary = grey100,
)

@Composable
fun GreyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        GreyThemeDarkColorScheme
    } else {
        GreyThemeLightColorScheme
    }
    AppTheme(colorScheme = colors, content = content)
}

@Composable
fun AppTheme(
    colorScheme: ColorScheme,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
