package com.example.shopapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color



private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,

)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,

    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,

)

private val DarkTextColorPalette by lazy {
    TextColors(
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        bodyText = Color.Black,
        subtitle1Text = Color.Gray
    )
}

private val LightTextColorPalette by lazy {
    TextColors(
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        bodyText = Color.Black,
        subtitle1Text = Color.Gray
    )
}

private val LightExtendedPalette by lazy {
    ExtendedColors()
}


@Composable
fun ShopAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val textColors = if (darkTheme) {
        LightTextColorPalette
    } else {
        DarkTextColorPalette
    }

    CompositionLocalProvider(
        LocalColorProvider provides colors,
        LocalTextColorsProvider provides textColors,
        LocalExtendedColorsProvider provides LightExtendedPalette,
        LocalTypography provides Typography,
        LocalShapes provides Shapes,
        content = content
    )

//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        shapes = Shapes,
//        content = content
//    )
}

object AppTheme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current

    val extendedColors: ExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalExtendedColorsProvider.current

    val textColors: TextColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTextColorsProvider.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

}


internal val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No default colors provided")
}
internal val LocalTextColorsProvider = staticCompositionLocalOf<TextColors> {
    error("No text colors provided")
}

internal val LocalExtendedColorsProvider = staticCompositionLocalOf<ExtendedColors> {
    error("No text colors provided")
}

internal val LocalShapes = staticCompositionLocalOf { Shapes() }
internal val LocalTypography = staticCompositionLocalOf { Typography() }
