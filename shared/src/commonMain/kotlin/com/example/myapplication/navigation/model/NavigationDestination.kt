package co.apoplawski96.kti.navigation.model

interface NavigationDestination {

    val route: String

    val genericRoute: String

    val arguments: List<NavArgument>
}

fun navigationDestinationOf(
    route: String,
    genericRoute: String,
    arguments: List<NavArgument> = emptyList()
) = object : NavigationDestination {
    override val route: String = route
    override val genericRoute: String = genericRoute
    override val arguments: List<NavArgument> = arguments
}
