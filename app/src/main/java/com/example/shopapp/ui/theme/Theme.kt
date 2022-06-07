package com.example.shopapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


private val DarkColorPalette = darkColors(
    primary = Color(0xFF101319),
    primaryVariant = Purple700,
    secondary = Teal200,

    background = Color(0xFF1E232A),
    surface = Color(0xFF1E232A),
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
        primaryTextColor = White,
        secondaryTextColor = Color.Gray,
        bodyText = White,
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

private val DarkExtendedPalette by lazy {
    ExtendedColors(
        buttonColor = Color(0XFF448AFE),
        outlineColor = White,
        cardBackgroundColor = Color(0xFF101319),
        subButtonColor = Color(0XFF448AFE),
        elevation = 0.dp,
        background = Color(0xFF1E232A),
        iconColor = White
    )
}

@Composable
fun ShopAppTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val textColors = if (isDarkTheme) {
        DarkTextColorPalette
    } else {
        LightTextColorPalette
    }

    val extendedColors = if (isDarkTheme) {
        DarkExtendedPalette
    } else {
        LightExtendedPalette
    }

    CompositionLocalProvider(
        LocalColorProvider provides colors,
        LocalTextColorsProvider provides textColors,
        LocalExtendedColorsProvider provides extendedColors,
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
