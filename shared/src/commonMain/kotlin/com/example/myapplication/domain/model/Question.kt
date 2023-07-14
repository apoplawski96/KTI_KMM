package com.example.myapplication.domain.model

import com.example.myapplication.domain.model.subcategory.SubCategory
import com.example.myapplication.domain.model.subcategory.AndroidSubCategory
import co.touchlab.kampkit.domain.model.Difficulty
import com.example.myapplication.domain.model.DeprecatedCategory

data class Question(
    val category: DeprecatedCategory,
    val subCategory: SubCategory = AndroidSubCategory.Basics,
    val difficulty: Difficulty = Difficulty.Beginner,
    val question: String,
    val title: String = question,
    val answer: String,
)
