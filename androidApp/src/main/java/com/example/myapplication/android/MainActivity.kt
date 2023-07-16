@file:OptIn(ExperimentalAnimationApi::class)

package com.example.myapplication.android

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.myapplication.navigation.Navigator
import co.touchlab.kampkit.injectLogger
import co.touchlab.kampkit.models.BreedViewModel
import co.touchlab.kermit.Logger
import com.example.myapplication.android.ui.theme.AppThemeMode
import com.example.myapplication.android.ui.theme.KTITheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    private val log: Logger by injectLogger("MainActivity")
    private val viewModel: BreedViewModel by viewModel()

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KTITheme(themeMode = AppThemeMode.LIGHT) {
                KTINavHost()
            }
        }
    }
}

fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}
