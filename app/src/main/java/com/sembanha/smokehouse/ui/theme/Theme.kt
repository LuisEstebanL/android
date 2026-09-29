package com.sembanha.smokehouse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = FlamePrimary,
    onPrimary = TextPrimary,
    primaryContainer = FlameBright,
    secondary = GoldPrimary,
    onSecondary = BgDarkest,
    background = BgBody,
    onBackground = TextPrimary,
    surface = BgSurface,
    onSurface = TextPrimary,
    surfaceVariant = BgCard,
    onSurfaceVariant = TextSecondary,
    outline = BorderSubtle
)

@Composable
fun SemBanhaSmokehouseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
