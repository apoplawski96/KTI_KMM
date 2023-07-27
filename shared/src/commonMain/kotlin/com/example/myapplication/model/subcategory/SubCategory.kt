package com.example.myapplication.model.subcategory

interface SubCategory {
    val keyName: String
    val id: String
    val displayName: String
}

fun getSubCategoryForId(id: String?): SubCategory? =
    allSubCategories.firstOrNull { subCategory: SubCategory ->
        subCategory.id == id
    }

val allSubCategories: List<SubCategory> = listOf(
    AndroidSubCategory.values().toList() + IOSSubCategory.values().toList()
).flatten()

enum class AndroidSubCategory(
    override val keyName: String,
    override val id: String,
    override val displayName: String
) : SubCategory{
    Basics(keyName = "Basic", id = "1", displayName = "Basics"),
    Coroutines(keyName = "Coroutines", id = "2", displayName = "Coroutines"),
    Compose(keyName = "Compose", id = "3", displayName = "Compose"),
    Rx(keyName = "Rx", id = "7", displayName = "RxJava"),
    StateAndSharedFlow(keyName = "StateAndSharedFlow", id = "8", "Flow, Shared Flow & State Flow"),
    AndroidPlatform(keyName = "Android Platform", id = "11", displayName = "Android Platform"),
    Lifecycle(keyName = "Lifecycle", id = "20", displayName = "Lifecycle"),
    Security(keyName = "Security", id = "21", displayName = "Security"),
    Architecture(keyName = "Architecture", id = "22", displayName = "Architecture"),
    Configuration(keyName = "Configuration", id = "23", displayName = "Configuration changes"),
    ViewModel(keyName = "ViewModel", id = "25", displayName = "View Model");
}

enum class IOSSubCategory(
    override val keyName: String,
    override val id: String,
    override val displayName: String
) : SubCategory{
    UserInterface(keyName = "User Interface", id = "101", displayName = "User interface"),
    CoreData(keyName = "Core Data", id = "102", displayName = "Core data"),
    Networking(keyName = "Networking", id = "103", displayName = "Networking");
}