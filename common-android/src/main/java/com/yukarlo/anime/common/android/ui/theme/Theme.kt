package com.yukarlo.anime.common.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = grey900,
    primaryVariant = grey900,
    secondary = teal200,
    secondaryVariant = yellow300,
    background = grey1000,
    surface = grey900,
    onPrimary = grey50,
    onSecondary = grey800,
    onBackground = grey50,
    onSurface = grey50
)

private val LightColorPalette = lightColors(
    primary = grey200,
    primaryVariant = grey700,
    secondary = teal200,
    secondaryVariant = yellow800,
    background = grey50,
    surface = grey200,
    onPrimary = grey700,
    onSecondary = grey50,
    onBackground = grey900,
    onSurface = grey900
)

@Composable
fun AnimeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    AppTheme(colors = colors, content = content)
}

private val GreyThemeLight = lightColors(
    surface = grey200,
    onPrimary = grey800,
)

private val GreyThemeDark = darkColors(
    surface = grey900,
    onPrimary = grey100,
)

@Composable
fun GreyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        GreyThemeDark
    } else {
        GreyThemeLight
    }
    AppTheme(colors = colors, content = content)
}

@Composable
fun AppTheme(
    colors: Colors,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
