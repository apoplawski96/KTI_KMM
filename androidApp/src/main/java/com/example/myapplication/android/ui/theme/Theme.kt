package com.example.myapplication.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import co.touchlab.kampkit.android.ui.theme.Shapes

val LightColorPalette = KTIColors(
    primary = White,
    secondary = GoalRed,
    onPrimary = Black,
    onSecondary = White,
    textMain = Black,
    textVariant = DarkGrey,
    textVariant2 = Grey,
    textVariant3 = Grey,
    textVariant4 = OffBlack,
    textVariant5 = Grey,
    textVariant6 = DarkGrey,
    backgroundRoot = LightGrey,
    backgroundSurface = White,
    backgroundSurfaceVariant = White,
    backgroundSurfaceVariant2 = LightGrey,
    backgroundSurfaceVariant3 = MidGrey,
    backgroundDim = Black30Alpha,
    divider = MidGrey,
    dividerVariant = LightGrey,
    dividerVariant2 = DarkGrey,
    accentGreen = Green,
    ripple = Grey,
    unselected = OffBlack,
    border = OffBlack,
    borderVariant = Black,
    error = ErrorRed,
    brightRed = BrightRed,
    yellow = Yellow,
    blue = Blue,
    lightBlue = LightBlue,
    orange = Orange,
    purple = Purple,
    mauve = Mauve,
    countDownTimerButton = DarkBlue,
    isDark = false
)

val DarkColorPalette = KTIColors(
    primary = Black,
    secondary = GoalRed,
    onPrimary = White,
    onSecondary = White,
    textMain = White,
    textVariant = LightGrey,
    textVariant2 = LightGrey,
    textVariant3 = Grey,
    textVariant4 = OffBlack,
    textVariant5 = MidGrey,
    textVariant6 = MidGrey,
    backgroundRoot = OffBlack,
    backgroundSurface = DarkGrey,
    backgroundSurfaceVariant = OffBlack,
    backgroundSurfaceVariant2 = DarkGrey,
    backgroundSurfaceVariant3 = Grey,
    backgroundDim = Black60Alpha,
    divider = DarkGrey,
    dividerVariant = Black,
    dividerVariant2 = Grey,
    accentGreen = Green,
    ripple = White,
    unselected = White,
    border = LightGrey,
    borderVariant = LightGrey,
    error = ErrorRed,
    brightRed = BrightRed,
    yellow = Yellow,
    blue = Blue,
    lightBlue = LightBlue,
    orange = Orange,
    purple = Purple,
    mauve = Mauve,
    countDownTimerButton = White,
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

