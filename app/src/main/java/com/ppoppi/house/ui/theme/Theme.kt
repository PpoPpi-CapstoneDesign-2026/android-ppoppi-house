package com.ppoppi.house.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

object PpoPpiTheme {
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

private val LocalTypography =
    staticCompositionLocalOf<Typography> { error("No Typography provided") }

val DarkColorScheme: ColorScheme =
    darkColorScheme(
        primary = Primary100,
        tertiary = Gray100,
    )

val LightColorScheme: ColorScheme =
    lightColorScheme(
        primary = Primary100,
        tertiary = Gray100,
    )

@Composable
fun platformColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
): ColorScheme {
    val context = LocalContext.current
    return when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}

@Composable
fun PpoPpiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val typography = Typography(density)
    val colorScheme = platformColorScheme(darkTheme, dynamicColor)

    CompositionLocalProvider(LocalTypography provides typography) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}
