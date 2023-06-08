package co.apoplawski96.kti.navigation.model

sealed interface NavArgument {
    data class StringArgument(val argument: String)
    data class IntArgument(val argument: Int)
}