package com.example.myapplication.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

val LightColorPalette = KTIColors(
    primary = white,
    secondary = kti_red,
    onPrimary = black,
    onSecondary = white,
    textMain = black,
    textVariant = kti_dark_grey,
    textVariant2 = kti_grey_variant,
    textVariant3 = kti_grey_variant,
    textVariant4 = kti_offblack,
    textVariant5 = kti_grey_variant,
    textVariant6 = kti_dark_grey,
    backgroundRoot = kti_light_grey,
    backgroundSurface = white,
    backgroundSurfaceVariant = white,
    backgroundSurfaceVariant2 = kti_light_grey,
    backgroundSurfaceVariant3 = kti_mid_grey,
    backgroundDim = kti_black_30alpha,
    divider = kti_mid_grey,
    dividerVariant = kti_light_grey,
    dividerVariant2 = kti_dark_grey,
    accentGreen = kti_green_variant,
    ripple = kti_grey_variant,
    unselected = kti_offblack,
    border = kti_offblack,
    borderVariant = black,
    error = kti_error_red,
    brightRed = kti_bright_red,
    yellow = kti_yellow,
    blue = kti_blue,
    lightBlue = kti_light_blue,
    orange = kti_orange,
    purple = kti_purple,
    mauve = kti_mauve,
    countDownTimerButton = kti_dark_blue,
    isDark = false
)

val DarkColorPalette = KTIColors(
    primary = black,
    secondary = kti_red,
    onPrimary = white,
    onSecondary = white,
    textMain = white,
    textVariant = kti_light_grey,
    textVariant2 = kti_light_grey,
    textVariant3 = kti_grey_variant,
    textVariant4 = kti_offblack,
    textVariant5 = kti_mid_grey,
    textVariant6 = kti_mid_grey,
    backgroundRoot = kti_offblack,
    backgroundSurface = kti_dark_grey,
    backgroundSurfaceVariant = kti_offblack,
    backgroundSurfaceVariant2 = kti_dark_grey,
    backgroundSurfaceVariant3 = kti_grey_variant,
    backgroundDim = kti_black_60alpha,
    divider = kti_dark_grey,
    dividerVariant = black,
    dividerVariant2 = kti_grey_variant,
    accentGreen = kti_green_variant,
    ripple = white,
    unselected = white,
    border = kti_light_grey,
    borderVariant = kti_light_grey,
    error = kti_error_red,
    brightRed = kti_bright_red,
    yellow = kti_yellow,
    blue = kti_blue,
    lightBlue = kti_light_blue,
    orange = kti_orange,
    purple = kti_purple,
    mauve = kti_mauve,
    countDownTimerButton = white,
    isDark = true
)

@Composable
fun KTITheme(
    themeMode: AppThemeMode,
    isPreview: Boolean = true,
    content: @Composable () -> Unit = {},
) {
    val darkTheme = themeMode.isDarkTheme()

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

//    if (isPreview.not()) {
//        val systemUiController = rememberSystemUiController()
//
//        SideEffect {
//            systemUiController.setStatusBarColor(
//                color = Color.Transparent,
//                darkIcons = darkTheme.not()
//            )
//            systemUiController.setNavigationBarColor(colors.primary)
//        }
//    }

    ProvideGoalColors(colors = colors) {
        MaterialTheme(
            colors = debugColors(),
            shapes = Shapes,
        ) {
            ProvideGoalRipple(darkTheme = darkTheme) {
                content()
            }
        }
    }
}

@Composable
fun AppThemeMode.isDarkTheme() =
    when (this) {
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
    }

object KTITheme {

    val colors: KTIColors
        @Composable
        get() = LocalKTIColors.current

    val typography: KTIColors
        @Composable
        get() = LocalKTIColors.current

    val dimens: KTIColors
        @Composable
        get() = LocalKTIColors.current
}

