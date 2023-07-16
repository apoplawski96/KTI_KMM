package com.example.myapplication.android.ui.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
internal fun ProvideGoalRipple(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRippleTheme provides GoalRippleTheme(darkTheme),
        content = content
    )
}

private class GoalRippleTheme(val darkTheme: Boolean) : RippleTheme {

    @Composable
    override fun defaultColor(): Color = KTITheme.colors.ripple

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Black,
            lightTheme = !darkTheme
        )
}
