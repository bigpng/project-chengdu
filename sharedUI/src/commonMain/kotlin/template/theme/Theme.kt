package org.zinc.chengdu.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import template.theme.BackgroundDark
import template.theme.BackgroundLight
import template.theme.ErrorContainerDark
import template.theme.ErrorContainerLight
import template.theme.ErrorDark
import template.theme.ErrorLight
import template.theme.InverseOnSurfaceDark
import template.theme.InverseOnSurfaceLight
import template.theme.InversePrimaryDark
import template.theme.InversePrimaryLight
import template.theme.InverseSurfaceDark
import template.theme.InverseSurfaceLight
import template.theme.OnBackgroundDark
import template.theme.OnBackgroundLight
import template.theme.OnErrorContainerDark
import template.theme.OnErrorContainerLight
import template.theme.OnErrorDark
import template.theme.OnErrorLight
import template.theme.OnPrimaryContainerDark
import template.theme.OnPrimaryContainerLight
import template.theme.OnPrimaryDark
import template.theme.OnPrimaryLight
import template.theme.OnSecondaryContainerDark
import template.theme.OnSecondaryContainerLight
import template.theme.OnSecondaryDark
import template.theme.OnSecondaryLight
import template.theme.OnSurfaceDark
import template.theme.OnSurfaceLight
import template.theme.OnSurfaceVariantDark
import template.theme.OnSurfaceVariantLight
import template.theme.OnTertiaryContainerDark
import template.theme.OnTertiaryContainerLight
import template.theme.OnTertiaryDark
import template.theme.OnTertiaryLight
import template.theme.OutlineDark
import template.theme.OutlineLight
import template.theme.OutlineVariantDark
import template.theme.OutlineVariantLight
import template.theme.PrimaryContainerDark
import template.theme.PrimaryContainerLight
import template.theme.PrimaryDark
import template.theme.PrimaryLight
import template.theme.ScrimDark
import template.theme.ScrimLight
import template.theme.SecondaryContainerDark
import template.theme.SecondaryContainerLight
import template.theme.SecondaryDark
import template.theme.SecondaryLight
import template.theme.SurfaceBrightDark
import template.theme.SurfaceBrightLight
import template.theme.SurfaceContainerDark
import template.theme.SurfaceContainerHighDark
import template.theme.SurfaceContainerHighLight
import template.theme.SurfaceContainerHighestDark
import template.theme.SurfaceContainerHighestLight
import template.theme.SurfaceContainerLight
import template.theme.SurfaceContainerLowDark
import template.theme.SurfaceContainerLowLight
import template.theme.SurfaceContainerLowestDark
import template.theme.SurfaceContainerLowestLight
import template.theme.SurfaceDark
import template.theme.SurfaceDimDark
import template.theme.SurfaceDimLight
import template.theme.SurfaceLight
import template.theme.SurfaceVariantDark
import template.theme.SurfaceVariantLight
import template.theme.TertiaryContainerDark
import template.theme.TertiaryContainerLight
import template.theme.TertiaryDark
import template.theme.TertiaryLight

data class ExtraColors(
    val selectNavColor: Color,
    val headerColor: Color
)

val LocalExtraColors = staticCompositionLocalOf<ExtraColors> {
    error("No ExtraColors provided")
}

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
    scrim = ScrimLight,
    inverseSurface = InverseSurfaceLight,
    inverseOnSurface = InverseOnSurfaceLight,
    inversePrimary = InversePrimaryLight,
    surfaceDim = SurfaceDimLight,
    surfaceBright = SurfaceBrightLight,
    surfaceContainerLowest = SurfaceContainerLowestLight,
    surfaceContainerLow = SurfaceContainerLowLight,
    surfaceContainer = SurfaceContainerLight,
    surfaceContainerHigh = SurfaceContainerHighLight,
    surfaceContainerHighest = SurfaceContainerHighestLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,
    scrim = ScrimDark,
    inverseSurface = InverseSurfaceDark,
    inverseOnSurface = InverseOnSurfaceDark,
    inversePrimary = InversePrimaryDark,
    surfaceDim = SurfaceDimDark,
    surfaceBright = SurfaceBrightDark,
    surfaceContainerLowest = SurfaceContainerLowestDark,
    surfaceContainerLow = SurfaceContainerLowDark,
    surfaceContainer = SurfaceContainerDark,
    surfaceContainerHigh = SurfaceContainerHighDark,
    surfaceContainerHighest = SurfaceContainerHighestDark,
)

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
internal fun AppTheme(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember(systemIsDark) { mutableStateOf(systemIsDark) }
    val isDark by isDarkState
    val extraColors = if (isDark) {
        ExtraColors(
            selectNavColor = Color(0xFFFDCE2A),
            headerColor = Color(0xFFFFFFFF)
        )
    } else {
        ExtraColors(
            selectNavColor = Color(0xFF9A870B),
            headerColor = Color(0xFF131212)
        )
    }

    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
        LocalExtraColors provides extraColors
    ) {
        val isDark by isDarkState
        onThemeChanged(!isDark)
        MaterialTheme(
            colorScheme = if (isDark) DarkColorScheme else LightColorScheme,
            content = { Surface(content = content) }
        )
    }
}
