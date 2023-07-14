package com.example.myapplication.domain

import com.example.myapplication.domain.model.subcategory.AndroidSubCategory
import com.example.myapplication.domain.model.subcategory.IOSSubCategory
import com.example.myapplication.domain.model.subcategory.SubCategory
import com.example.myapplication.domain.model.subcategory.TopCategory

class SubCategoriesRepository {

    operator fun invoke(category: TopCategory): List<SubCategory> = when(category) {
        TopCategory.ANDROID -> AndroidSubCategory.values().toList()
        TopCategory.IOS -> IOSSubCategory.values().toList()
    }
}