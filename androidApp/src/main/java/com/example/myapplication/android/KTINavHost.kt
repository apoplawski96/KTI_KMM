@file:OptIn(ExperimentalAnimationApi::class)

package com.example.myapplication.android

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import co.apoplawski96.kti.navigation.model.NavigationCommand
import com.example.myapplication.android.ui.categories.CategoriesScreen
import com.example.myapplication.android.ui.home.HomeScreen
import com.example.myapplication.android.ui.questions.list.ListScreen
import com.example.myapplication.android.ui.subcategory.SubCategoriesScreen
import com.example.myapplication.android.ui.welcome.WelcomeScreen
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.model.subcategory.getSubCategoryForId
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import com.example.myapplication.navigation.model.NavigationArgument
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
        startDestination = Destinations.WelcomeScreen.route
    ) {
        welcomeScreen()
        menuScreen()
        listScreen()
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
    this.map { navArgument ->
        navArgument(name = navArgument.key) {
            type = navArgument.getArgType()
            nullable = navArgument.nullable
        }
    }

private fun NavigationArgument.getArgType(): NavType<out Any?> = when (this) {
    is NavigationArgument.IntArgument -> NavType.IntType
    is NavigationArgument.StringArgument -> NavType.StringType
}

private fun NavGraphBuilder.welcomeScreen() {
    composable(route = Destinations.WelcomeScreen.route) {
        WelcomeScreen()
    }
}

private fun NavGraphBuilder.menuScreen() {
    composable(route = Destinations.Menu.route) {
        HomeScreen()
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
        SubCategoriesScreen(topCategory = TopCategory.getForId(categoryId.toString()))
    }
}

private fun NavGraphBuilder.listScreen() {
    composable(
        route = Destinations.QuestionsList.route,
        arguments = Destinations.QuestionsList.arguments.toAndroidNavArgs()
    ) { backStackEntry ->
        val categoryId: String? =
            backStackEntry.arguments?.getString(Destinations.QuestionsList.topCategoryIdArg)
        val subCategoryId: String? =
            backStackEntry.arguments?.getString(Destinations.QuestionsList.subCategoryIdArg)
        ListScreen(
            topCategory = TopCategory.getForId(categoryId.toString()),
            subCategory = getSubCategoryForId(subCategoryId)
        )
    }
}