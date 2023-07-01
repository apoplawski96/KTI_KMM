@file:OptIn(
    ExperimentalAnimationApi::class, ExperimentalAnimationApi::class,
    ExperimentalAnimationApi::class
)

package com.example.myapplication.android

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import co.apoplawski96.kti.navigation.model.NavigationCommand
import com.example.myapplication.android.ui.categories.CategoriesScreen
import co.touchlab.kampkit.android.ui.home.MenuScreen
import com.example.myapplication.android.ui.questions.list.ListScreen
import co.touchlab.kampkit.android.ui.quiz.QuizScreen
import com.example.myapplication.android.ui.subcategory.SubCategoryScreen
import com.example.myapplication.navigation.model.NavigationArgument
import com.example.myapplication.questions.model.subcategory.SubCategory
import com.example.myapplication.questions.model.subcategory.TopCategory
import com.example.myapplication.questions.model.subcategory.getSubCategoryForId
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
        subCategoriesScreen()
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

private fun List<NavigationArgument>.toAndroidNavArgs(): List<NamedNavArgument> =
    this.map { navArgument -> navArgument(name = navArgument.key) { navArgument.getArgType() } }

private fun NavigationArgument.getArgType(): NavType<out Any?> = when(this) {
    is NavigationArgument.IntArgument -> NavType.IntType
    is NavigationArgument.StringArgument -> NavType.StringType
}

private fun NavGraphBuilder.menuScreen() {
    composable(route = Destinations.Menu.route) {
        MenuScreen()
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

private fun NavGraphBuilder.subCategoriesScreen() {
    composable(
        route = Destinations.SubCategories.route,
        arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
    ) { backStackEntry ->
        val categoryId: String? =
            backStackEntry.arguments?.getString(Destinations.SubCategories.categoryIdArgName)
        SubCategoryScreen(topCategory = TopCategory.getForId(categoryId.toString()))
    }
}

private fun NavGraphBuilder.listScreen() {
    composable(
        route = Destinations.QuestionsList.route,
        arguments = Destinations.QuestionsList.arguments.toAndroidNavArgs()
    ) { backStackEntry ->
        val categoryId: String? = backStackEntry.arguments?.getString(Destinations.QuestionsList.topCategoryIdArg)
        val subCategoryId: String? = backStackEntry.arguments?.getString(Destinations.QuestionsList.subCategoryIdArg)
        ListScreen(category = TopCategory.getForId(categoryId.toString()), subCategory = getSubCategoryForId(subCategoryId))
    }
}