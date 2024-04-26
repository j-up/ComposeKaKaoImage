package com.image.jm.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

object ResourceObject {
    val LocalColors = staticCompositionLocalOf { lightImageColors }
    val LocalTypography = staticCompositionLocalOf { ImageTypography(lightImageColors) }

    @Composable
    fun ImageTheme(
        isDarkMode: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        val colors = if (isDarkMode) darkImageColors else lightImageColors
        val typography = ImageTypography(colors)

        CompositionLocalProvider(
            LocalColors provides colors,
            LocalTypography provides typography,
            content = content
        )
    }
}
