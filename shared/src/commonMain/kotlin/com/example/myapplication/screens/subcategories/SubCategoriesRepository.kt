package com.example.myapplication.screens.subcategories

import com.example.myapplication.model.subcategory.AndroidSubCategory
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory

class SubCategoriesRepository {

    operator fun invoke(category: TopCategory): List<SubCategory> = when(category) {
        TopCategory.ANDROID -> AndroidSubCategory.values().toList()
//        TopCategory.IOS -> IOSSubCategory.values().toList()
        else -> emptyList()
    }
}