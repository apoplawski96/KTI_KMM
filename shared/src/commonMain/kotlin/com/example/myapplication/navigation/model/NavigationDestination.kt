package co.apoplawski96.kti.navigation.model

import com.example.myapplication.navigation.model.NavigationArgument

interface NavigationDestination {

    val route: String

    val genericRoute: String

    val arguments: List<NavigationArgument>
}

fun navigationDestinationOf(
    route: String,
    genericRoute: String,
    arguments: List<NavigationArgument> = emptyList()
) = object : NavigationDestination {
    override val route: String = route
    override val genericRoute: String = genericRoute
    override val arguments: List<NavigationArgument> = arguments
}
