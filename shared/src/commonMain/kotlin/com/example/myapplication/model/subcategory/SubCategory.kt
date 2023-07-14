package com.example.myapplication.model.subcategory

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
) : SubCategory {
    Basics(displayName = "Basic", id = "1"),
    Coroutines(displayName = "Coroutines", id = "2"),
    Compose(displayName = "Compose", id = "3");
}

enum class IOSSubCategory(override val displayName: String, override val id: String) : SubCategory {
    UserInterface(displayName = "User Interface", id = "101"),
    CoreData(displayName = "Core Data", id = "102"),
    Networking(displayName = "Networking", id = "103");
}

fun getSubCategoryForId(id: String?): SubCategory? =
    allSubCategories.firstOrNull { subCategory: SubCategory ->
        subCategory.id == id
    }