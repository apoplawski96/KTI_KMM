package com.example.myapplication.feature.subcategories

import com.example.myapplication.model.domain.AndroidSubCategory
import com.example.myapplication.model.domain.IOSSubCategory
import com.example.myapplication.model.domain.SubCategory
import com.example.myapplication.model.domain.TopCategory

class SubCategoriesRepository {

    fun getSubCategories(category: TopCategory): List<SubCategory> = when(category) {
        TopCategory.ANDROID -> AndroidSubCategory.values().toList()
        TopCategory.IOS -> IOSSubCategory.values().toList()
        else -> emptyList()
    }
}