@file:OptIn(ExperimentalAnimationApi::class)

package com.example.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.myapplication.android.screens.theme.AppThemeMode
import com.example.myapplication.android.screens.theme.KTITheme
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KTITheme(themeMode = AppThemeMode.LIGHT) {
                KTINavHost()
            }
        }
    }
}