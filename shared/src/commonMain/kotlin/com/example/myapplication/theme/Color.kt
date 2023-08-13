package com.example.myapplication.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

///* KTI V1 */
//val kti_primary = Color(0xFF141414)
//val kti_dark_primary = Color(0xDA000000)
//val kti_light_primary = Color(0xFF23313c)
//val kti_text_icons = Color(0xFFFFFFFF)
//val kti_accent_color = Color(0xFF79CF7D)
//val kti_primary_text = Color(0xFFFFFFFF)
//val kti_secondary_text = Color(0xFFD5D5D5)
//val kti_divider = Color(0xFFBDBDBD)
//val kti_green = Color(0xFF588A61)

/* KTI V2 */
val kti_grayish = Color(0xFF2E2E2E)
val kti_grayish_variant = Color(0xFF313131)
val kti_soft_white = Color(0xFFF5F5F0)
val kti_grayish_light = Color(0xFF23313c)
val kti_text_icons = Color(0xFFF5F5F0)
val kti_accent = Color(0xFFD55951)
val kti_soft_black = Color(0xFF15191F)
val kti_secondary_text = Color(0xFF15191F)
val kti_divider = Color(0xFFBDBDBD)
val kti_green = Color(0xFF1F5F2B)

val kti_grey = Color(0xFFCFCFCF)
val kti_background_grey = Color(0xFFDDDDDD)

val black = Color(0xFF000000)
val kti_offblack = Color(0xFF292929)
val white = Color(0xFFFFFFFF)

val kti_red = Color(0xFFF0705A)
val kti_error_red = Color(0xFFBC4042)
val kti_bright_red = Color(0xFFAF1212)
val kti_green_variant = Color(0xFF20A567)
val kti_yellow = Color(0xFFDFB125)
val kti_blue = Color(0xFF6EA2DD)
val kti_light_blue = Color(0xFF74C3CE)
val kti_dark_blue = Color(0xFF003DC5)
val kti_orange = Color(0xFFF29A41)
val kti_purple = Color(0xFF313888)
val kti_mauve = Color(0xFF8A4B7B)

val kti_dark_grey = Color(0xFF343434)
val kti_grey_variant = Color(0xFF828282)
val kti_mid_grey = Color(0xFFB6B7BB)
val kti_light_grey = Color(0xFFF6F6F6)

val kti_black_60alpha = Color(0x99000000)
val kti_black_30alpha = Color(0x4D000000)

@Stable
class KTIColors(
    primary: Color,
    secondary: Color,
    onPrimary: Color,
    onSecondary: Color,
    textMain: Color,
    textVariant: Color,
    textVariant2: Color,
    textVariant3: Color,
    textVariant4: Color,
    textVariant5: Color,
    textVariant6: Color,
    backgroundRoot: Color,
    backgroundSurface: Color,
    backgroundSurfaceVariant: Color,
    backgroundSurfaceVariant2: Color,
    backgroundSurfaceVariant3: Color,
    backgroundDim: Color,
    divider: Color,
    dividerVariant: Color,
    dividerVariant2: Color,
    accentGreen: Color,
    ripple: Color,
    unselected: Color,
    border: Color,
    borderVariant: Color,
    error: Color,
    brightRed: Color,
    yellow: Color,
    blue: Color,
    lightBlue: Color,
    orange: Color,
    purple: Color,
    mauve: Color,
    countDownTimerButton: Color,
    isDark: Boolean,
) {
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var textMain by mutableStateOf(textMain)
        private set
    var textVariant by mutableStateOf(textVariant)
        private set
    var textVariant2 by mutableStateOf(textVariant2)
        private set
    var textVariant3 by mutableStateOf(textVariant3)
        private set
    var textVariant4 by mutableStateOf(textVariant4)
        private set
    var textVariant5 by mutableStateOf(textVariant5)
        private set
    var textVariant6 by mutableStateOf(textVariant6)
        private set
    var backgroundRoot by mutableStateOf(backgroundRoot)
        private set
    var backgroundSurface by mutableStateOf(backgroundSurface)
        private set
    var backgroundSurfaceVariant by mutableStateOf(backgroundSurfaceVariant)
        private set
    var backgroundSurfaceVariant2 by mutableStateOf(backgroundSurfaceVariant2)
        private set
    var backgroundSurfaceVariant3 by mutableStateOf(backgroundSurfaceVariant3)
        private set
    var backgroundDim by mutableStateOf(backgroundDim)
        private set
    var divider by mutableStateOf(divider)
        private set
    var dividerVariant by mutableStateOf(dividerVariant)
        private set
    var dividerVariant2 by mutableStateOf(dividerVariant2)
        private set
    var accentGreen by mutableStateOf(accentGreen)
        private set
    var ripple by mutableStateOf(ripple)
        private set
    var unselected by mutableStateOf(unselected)
        private set
    var border by mutableStateOf(border)
        private set
    var borderVariant by mutableStateOf(borderVariant)
        private set
    var error by mutableStateOf(error)
        private set
    var brightRed by mutableStateOf(brightRed)
        private set
    var yellow by mutableStateOf(yellow)
        private set
    var blue by mutableStateOf(blue)
        private set
    var lightBlue by mutableStateOf(lightBlue)
        private set
    var orange by mutableStateOf(orange)
        private set
    var purple by mutableStateOf(purple)
        private set
    var mauve by mutableStateOf(mauve)
        private set
    var countDownTimerButton by mutableStateOf(countDownTimerButton)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun copy(): KTIColors = KTIColors(
        primary = primary,
        secondary = secondary,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        textMain = textMain,
        textVariant = textVariant,
        textVariant2 = textVariant2,
        textVariant3 = textVariant3,
        textVariant4 = textVariant4,
        textVariant5 = textVariant5,
        textVariant6 = textVariant6,
        backgroundRoot = backgroundRoot,
        backgroundSurface = backgroundSurface,
        backgroundSurfaceVariant = backgroundSurfaceVariant,
        backgroundSurfaceVariant2 = backgroundSurfaceVariant2,
        backgroundSurfaceVariant3 = backgroundSurfaceVariant3,
        backgroundDim = backgroundDim,
        divider = divider,
        dividerVariant = dividerVariant,
        dividerVariant2 = dividerVariant2,
        accentGreen = accentGreen,
        ripple = ripple,
        unselected = unselected,
        border = border,
        borderVariant = borderVariant,
        error = error,
        brightRed = brightRed,
        yellow = yellow,
        blue = blue,
        lightBlue = lightBlue,
        orange = orange,
        purple = purple,
        mauve = mauve,
        countDownTimerButton = countDownTimerButton,
        isDark = isDark
    )

    fun update(other: KTIColors) {
        primary = other.primary
        secondary = other.secondary
        onPrimary = other.onPrimary
        onSecondary = other.onSecondary
        textMain = other.textMain
        textVariant = other.textVariant
        textVariant2 = other.textVariant2
        textVariant3 = other.textVariant3
        textVariant4 = other.textVariant4
        textVariant5 = other.textVariant5
        textVariant6 = other.textVariant6
        backgroundRoot = other.backgroundRoot
        backgroundSurface = other.backgroundSurface
        backgroundSurfaceVariant = other.backgroundSurfaceVariant
        backgroundSurfaceVariant2 = other.backgroundSurfaceVariant2
        backgroundSurfaceVariant3 = other.backgroundSurfaceVariant3
        backgroundDim = other.backgroundDim
        divider = other.divider
        dividerVariant = other.dividerVariant
        dividerVariant2 = other.dividerVariant2
        accentGreen = other.accentGreen
        ripple = other.ripple
        unselected = other.unselected
        border = other.border
        borderVariant = other.borderVariant
        error = other.error
        brightRed = other.brightRed
        yellow = other.yellow
        blue = other.blue
        lightBlue = other.lightBlue
        orange = other.orange
        purple = other.purple
        mauve = other.mauve
        countDownTimerButton = other.countDownTimerButton
        isDark = other.isDark
    }
}

@Composable
internal fun ProvideKTIColors(
    colors: KTIColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors.copy() }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalKTIColors provides colorPalette, content = content)
}

val LocalKTIColors = staticCompositionLocalOf<KTIColors> {
    error("No KTIColorPalette provided")
}

internal fun debugColors(debugColor: Color = Color.Magenta) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = false
)
