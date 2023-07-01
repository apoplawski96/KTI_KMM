package com.example.myapplication.navigation.model

sealed class NavigationArgument(open val key: String) {
    data class StringArgument(override val key: String, val argument: String = "") : NavigationArgument(key = key)
    data class IntArgument(override val key: String, val argument: Int = -1) : NavigationArgument(key = key)
}