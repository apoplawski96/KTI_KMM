package com.example.myapplication.model.subcategory

interface SubCategory {
    val displayName: String
    val id: String
}

object Random : SubCategory {
    override val displayName: String
        get() = "Random"
    override val id: String
        get() = "666"
}

val allSubCategories: List<SubCategory> = listOf(
    AndroidSubCategory.values().toList() + IOSSubCategory.values().toList() + Random
).flatten()

enum class AndroidSubCategory(
    override val displayName: String,
    override val id: String
) : SubCategory {
    Basics(displayName = "Basics", id = "1"),
    Coroutines(displayName = "Coroutines", id = "2"),
    Compose(displayName = "Compose", id = "3");
}

enum class IOSSubCategory(override val displayName: String, override val id: String) : SubCategory {
    Basics(displayName = "Basics", id = "101"),
    OS(displayName = "OS", id = "102");
}

fun getSubCategoryForId(id: String?): SubCategory? =
    allSubCategories.firstOrNull { subCategory: SubCategory ->
        subCategory.id == id
    }