package com.yukarlo.anime.common.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = grey900,
    primaryVariant = grey900,
    secondary = teal200,
    background = grey900,
    surface = grey900,
    onPrimary = grey50,
    onSecondary = grey50,
    onBackground = grey50,
    onSurface = grey50
)

private val LightColorPalette = lightColors(
    primary = grey200,
    primaryVariant = grey700,
    secondary = teal200,
    background = grey50,
    surface = grey200,
    onPrimary = grey200,
    onSecondary = grey200,
    onBackground = grey900,
    onSurface = grey900
)

@Composable
fun AnimeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}