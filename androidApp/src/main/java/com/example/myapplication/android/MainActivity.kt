@file:OptIn(ExperimentalAnimationApi::class)

package com.example.myapplication.android

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.myapplication.navigation.Navigator
import co.touchlab.kampkit.android.ui.theme.KaMPKitTheme
import co.touchlab.kampkit.injectLogger
import co.touchlab.kampkit.loadJSONFromAssets
import co.touchlab.kampkit.models.BreedViewModel
import co.touchlab.kermit.Logger
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
            KaMPKitTheme {
                KTINavHost()
            }
        }

        val json = this.applicationContext.loadJSONFromAssets("questions.json")
        Log.d("2137", json)
    }
}

fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}
