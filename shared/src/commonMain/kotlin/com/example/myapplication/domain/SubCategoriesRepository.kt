package com.example.myapplication.domain

import com.example.myapplication.questions.model.subcategory.AndroidSubCategory
import com.example.myapplication.questions.model.subcategory.IOSSubCategory
import com.example.myapplication.questions.model.subcategory.SubCategory
import com.example.myapplication.questions.model.subcategory.TopCategory

class SubCategoriesRepository {

    operator fun invoke(category: TopCategory): List<SubCategory> = when(category) {
        TopCategory.ANDROID -> AndroidSubCategory.values().toList()
        TopCategory.IOS -> IOSSubCategory.values().toList()
    }
}