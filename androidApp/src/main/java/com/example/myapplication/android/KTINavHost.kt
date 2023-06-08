@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class,
    ExperimentalAnimationApi::class
)

package com.example.myapplication.android

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import co.apoplawski96.kti.navigation.Navigator
import co.apoplawski96.kti.navigation.model.Destinations
import co.apoplawski96.kti.navigation.model.NavigationCommand
import co.touchlab.kampkit.android.ui.categories.CategoriesScreen
import co.touchlab.kampkit.android.ui.home.MenuScreen
import co.touchlab.kampkit.android.ui.questions.list.ListScreen
import co.touchlab.kampkit.android.ui.quiz.QuizScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.get

@ExperimentalAnimationApi
@Composable
fun KTINavHost(navigator: Navigator = get()) {
    val animatedNavController = rememberAnimatedNavController()

    LaunchedEffect(null) {
        navigator.commands.collect { command ->
            handleNavigationCommand(command = command, navController = animatedNavController)
        }
    }

    AnimatedNavHost(
        navController = animatedNavController,
        startDestination = Destinations.Menu.route
    ) {
        menuScreen()
        listScreen()
        quizScreen()
        categoriesScreen()
    }
}

private fun handleNavigationCommand(
    command: NavigationCommand,
    navController: NavController,
) {
    if (command.route.isEmpty() && command !is NavigationCommand.NavigateBack) return

    when (command) {
        is NavigationCommand.NavigateBack -> {
            navController.popBackStack()
        }
        is NavigationCommand.Navigate -> {
            navController.navigate(route = command.route) {
                launchSingleTop = command.singleTop
            }
        }
        is NavigationCommand.PopUpTo -> {
            navController.popBackStack(
                route = command.genericRoute,
                inclusive = command.inclusive,
                saveState = command.saveState
            )
        }
        is NavigationCommand.SwitchBackstack -> {
            navController.navigate(route = command.route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }
}

private fun NavGraphBuilder.menuScreen() {
    composable(route = Destinations.Menu.route) {
        MenuScreen()
    }
}

private fun NavGraphBuilder.listScreen() {
    composable(route = Destinations.QuestionsList.route) {
        ListScreen()
    }
}

private fun NavGraphBuilder.quizScreen() {
    composable(route = Destinations.QuestionsQuiz.route) {
        QuizScreen()
    }
}

private fun NavGraphBuilder.categoriesScreen() {
    composable(route = Destinations.Categories.route) {
        CategoriesScreen()
    }
}