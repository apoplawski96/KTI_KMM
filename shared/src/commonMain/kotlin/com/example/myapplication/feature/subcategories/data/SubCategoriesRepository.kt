package com.example.myapplication.feature.subcategories.data

import com.example.myapplication.model.AndroidSubCategory
import com.example.myapplication.model.IOSSubCategory
import com.example.myapplication.model.SubCategory
import com.example.myapplication.model.TopCategory

class SubCategoriesRepository {

    fun getSubCategories(category: TopCategory): List<SubCategory> = when(category) {
        TopCategory.ANDROID -> AndroidSubCategory.values().toList()
        TopCategory.IOS -> IOSSubCategory.values().toList()
        else -> emptyList()
    }
}