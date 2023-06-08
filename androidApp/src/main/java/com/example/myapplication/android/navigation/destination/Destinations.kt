package com.example.myapplication.android.navigation.destination

import co.apoplawski96.kti.navigation.model.navigationDestinationOf

object Destinations {

    object DogsScreen {
        val route: String = "dogs_screen"
        fun destination() = navigationDestinationOf(route, route)
    }

    object QuestionsListScreen {
        val route: String = "questions_list_screen"
        fun destination() = navigationDestinationOf(route, route)
    }

    object QuestionsQuizScreen {
        val route: String = "questions_quiz_screen"
        fun destination() = navigationDestinationOf(route, route)
    }

    object HomeScreen {
        val route: String = "home_screen"
        fun destination() = navigationDestinationOf(route, route)
    }
}
