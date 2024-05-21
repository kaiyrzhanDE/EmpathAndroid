package kaiyrzhan.de.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DARK_PRIMARY,
    onPrimary = DARK_ON_PRIMARY,
    primaryContainer = DARK_PRIMARY_CONTAINER,
    onPrimaryContainer = DARK_ON_PRIMARY_CONTAINER,
    inversePrimary = DARK_INVERSE_PRIMARY,
    secondary = DARK_SECONDARY,
    onSecondary = DARK_ON_SECONDARY,
    secondaryContainer = DARK_SECONDARY_CONTAINER,
    onSecondaryContainer = DARK_ON_SECONDARY_CONTAINER,
    tertiary = DARK_TERTIARY,
    onTertiary = DARK_ON_TERTIARY,
    tertiaryContainer = DARK_TERTIARY_CONTAINER,
    onTertiaryContainer = DARK_ON_TERTIARY_CONTAINER,
    background = DARK_BACKGROUND,
    onBackground = DARK_ON_BACKGROUND,
    surface = DARK_SURFACE,
    onSurface = DARK_ON_SURFACE,
    surfaceVariant = DARK_SURFACE_VARIANT,
    onSurfaceVariant = DARK_ON_SURFACE_VARIANT,
    surfaceTint = DARK_SURFACE_TINT,
    inverseSurface = DARK_INVERSE_SURFACE,
    inverseOnSurface = DARK_INVERSE_ON_SURFACE,
    error = DARK_ERROR,
    onError = DARK_ON_ERROR,
    errorContainer = DARK_ERROR_CONTAINER,
    onErrorContainer = DARK_ON_ERROR_CONTAINER,
    outline = DARK_OUTLINE,
    outlineVariant = DARK_OUTLINE_VARIANT,
    scrim = DARK_SCRIM,
    surfaceBright = DARK_SURFACE_BRIGHT,
    surfaceContainer = DARK_SURFACE_CONTAINER,
    surfaceContainerHigh = DARK_SURFACE_CONTAINER_HIGH,
    surfaceContainerHighest = DARK_SURFACE_CONTAINER_HIGHEST,
    surfaceContainerLow = DARK_SURFACE_CONTAINER_LOW,
    surfaceContainerLowest = DARK_SURFACE_CONTAINER_LOWEST,
    surfaceDim = DARK_SURFACE_DIM
)

private val LightColorScheme = lightColorScheme(
    primary = LIGHT_PRIMARY,
    onPrimary = LIGHT_ON_PRIMARY,
    primaryContainer = LIGHT_PRIMARY_CONTAINER,
    onPrimaryContainer = LIGHT_ON_PRIMARY_CONTAINER,
    inversePrimary = LIGHT_INVERSE_PRIMARY,
    secondary = LIGHT_SECONDARY,
    onSecondary = LIGHT_ON_SECONDARY,
    secondaryContainer = LIGHT_SECONDARY_CONTAINER,
    onSecondaryContainer = LIGHT_ON_SECONDARY_CONTAINER,
    tertiary = LIGHT_TERTIARY,
    onTertiary = LIGHT_ON_TERTIARY,
    tertiaryContainer = LIGHT_TERTIARY_CONTAINER,
    onTertiaryContainer = LIGHT_ON_TERTIARY_CONTAINER,
    background = LIGHT_BACKGROUND,
    onBackground = LIGHT_ON_BACKGROUND,
    surface = LIGHT_SURFACE,
    onSurface = LIGHT_ON_SURFACE,
    surfaceVariant = LIGHT_SURFACE_VARIANT,
    onSurfaceVariant = LIGHT_ON_SURFACE_VARIANT,
    surfaceTint = LIGHT_SURFACE_TINT,
    inverseSurface = LIGHT_INVERSE_SURFACE,
    inverseOnSurface = LIGHT_INVERSE_ON_SURFACE,
    error = LIGHT_ERROR,
    onError = LIGHT_ON_ERROR,
    errorContainer = LIGHT_ERROR_CONTAINER,
    onErrorContainer = LIGHT_ON_ERROR_CONTAINER,
    outline = LIGHT_OUTLINE,
    outlineVariant = LIGHT_OUTLINE_VARIANT,
    scrim = LIGHT_SCRIM,
    surfaceBright = LIGHT_SURFACE_BRIGHT,
    surfaceContainer = LIGHT_SURFACE_CONTAINER,
    surfaceContainerHigh = LIGHT_SURFACE_CONTAINER_HIGH,
    surfaceContainerHighest = LIGHT_SURFACE_CONTAINER_HIGHEST,
    surfaceContainerLow = LIGHT_SURFACE_CONTAINER_LOW,
    surfaceContainerLowest = LIGHT_SURFACE_CONTAINER_LOWEST,
    surfaceDim = LIGHT_SURFACE_DIM
)


@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = textStyles,
        content = content,
    )
}