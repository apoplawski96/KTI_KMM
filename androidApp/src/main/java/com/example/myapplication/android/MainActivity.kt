@file:OptIn(ExperimentalAnimationApi::class)

package com.example.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.myapplication.KMMApp
import com.example.myapplication.android.theme.AppThemeMode
import com.example.myapplication.theme.KTITheme
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KTITheme(themeMode = AppThemeMode.LIGHT) {
                // Android Compose implementation
//                KTINavHost()
                // KMM Compose Multiplatform implementation
                KMMApp()
            }
        }
    }
}