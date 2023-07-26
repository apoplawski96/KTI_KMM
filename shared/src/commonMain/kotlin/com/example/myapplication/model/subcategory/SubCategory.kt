package com.example.myapplication.model.subcategory

interface CardDisplayable {
    val displayName: String
}

interface SubCategory {
    val keyName: String
    val id: String
    val displayName: String
}

val allSubCategories: List<SubCategory> = listOf(
    AndroidSubCategory.values().toList() + IOSSubCategory.values().toList()
).flatten()

enum class AndroidSubCategory(
    override val keyName: String,
    override val id: String,
    override val displayName: String
) : SubCategory, CardDisplayable {
    Basics(keyName = "Basic", id = "1", displayName = "Basics"),
    Coroutines(keyName = "Coroutines", id = "2", displayName = "Coroutines"),
    Compose(keyName = "Compose", id = "3", displayName = "Compose"),
    Rx(keyName = "Rx", id = "7", displayName = "RxJava"),
    StateAndSharedFlow(keyName = "StateAndSharedFlow", id = "8", "State & Shared Flow"),
    AndroidPlatform(keyName = "Android Platform", id = "11", displayName = "Android Platform"),
    Lifecycle(keyName = "Lifecycle", id = "20", displayName = "Lifecycle"),
    Security(keyName = "Security", id = "21", displayName = "Security");
}

enum class IOSSubCategory(
    override val keyName: String,
    override val id: String,
    override val displayName: String
) : SubCategory, CardDisplayable {
    UserInterface(keyName = "User Interface", id = "101", displayName = "User interface"),
    CoreData(keyName = "Core Data", id = "102", displayName = "Core data"),
    Networking(keyName = "Networking", id = "103", displayName = "Networking");
}

fun getSubCategoryForId(id: String?): SubCategory? =
    allSubCategories.firstOrNull { subCategory: SubCategory ->
        subCategory.id == id
    }