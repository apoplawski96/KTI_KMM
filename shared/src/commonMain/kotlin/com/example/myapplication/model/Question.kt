package com.example.myapplication.model

import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.AndroidSubCategory
import com.example.myapplication.model.subcategory.TopCategory

data class Question(
    val id: Int = -1,
    val topCategoryId: Int = -1,
    val subCategoryId: Int = -1,
    val category: DeprecatedCategory,
    val topCategory: TopCategory = TopCategory.ANDROID,
    val subCategory: SubCategory? = AndroidSubCategory.Basics,
    val difficulty: Difficulty = Difficulty.Beginner,
    val question: String,
    val answer: String,
)
