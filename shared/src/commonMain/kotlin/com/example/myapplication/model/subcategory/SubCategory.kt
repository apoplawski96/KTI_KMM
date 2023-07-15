package com.example.myapplication.model.subcategory

interface CardDisplayable {
    val displayName: String
}

interface SubCategory {
    val displayName: String
    val id: String
}

val allSubCategories: List<SubCategory> = listOf(
    AndroidSubCategory.values().toList() + IOSSubCategory.values().toList()
).flatten()

enum class AndroidSubCategory(
    override val displayName: String,
    override val id: String
) : SubCategory, CardDisplayable {
    Basics(displayName = "Basic", id = "1"),
    Coroutines(displayName = "Coroutines", id = "2"),
    Compose(displayName = "Compose", id = "3"),
    Rx(displayName = "Rx", id = "7"),
    StateAndSharedFlow(displayName = "StateAndSharedFlow", id = "8");
}

enum class IOSSubCategory(override val displayName: String, override val id: String) : SubCategory, CardDisplayable {
    UserInterface(displayName = "User Interface", id = "101"),
    CoreData(displayName = "Core Data", id = "102"),
    Networking(displayName = "Networking", id = "103");
}

fun getSubCategoryForId(id: String?): SubCategory? =
    allSubCategories.firstOrNull { subCategory: SubCategory ->
        subCategory.id == id
    }