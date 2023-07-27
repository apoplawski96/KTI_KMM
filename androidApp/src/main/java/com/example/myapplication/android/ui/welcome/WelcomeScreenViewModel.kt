package com.example.myapplication.android.ui.welcome

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations

class WelcomeScreenViewModel(private val navigator: Navigator) : ViewModel() {

    fun navigateToHomeScreen() {
        navigator.navigate(Destinations.Menu)
    }

    fun navigateToAuthenticationScreen() {
        // TODO
    }
}