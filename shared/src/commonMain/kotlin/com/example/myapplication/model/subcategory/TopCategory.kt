package com.example.myapplication.model.subcategory

enum class TopCategory(
    val id: String,
    val displayName: String,
    val subCategories: List<SubCategory> = emptyList(),
) {
    ANDROID(id = "1", displayName = "Android", subCategories = AndroidSubCategory.values().toList()),
    IOS(id = "2", displayName = "iOS");
//    GIT(3, "GIT"),
//    REST(4, "Rest"),
//    DATA_STRUCTURES(5, "Data structures"),
//    ALGORITHMS(6, "Algorithms");

    companion object {

        fun getForId(id: String): TopCategory? = values().toList().firstOrNull { category -> id == category.id }
    }
}

