package com.example.myapplication.questions.model

import com.example.myapplication.questions.model.subcategory.SubCategory
import com.example.myapplication.questions.model.subcategory.AndroidSubCategory
import co.touchlab.kampkit.domain.model.Difficulty
import com.example.myapplication.questions.model.DeprecatedCategory

data class Question(
    val category: DeprecatedCategory,
    val subCategory: SubCategory = AndroidSubCategory.Basics,
    val difficulty: Difficulty = Difficulty.Beginner,
    val question: String,
    val title: String = question,
    val answer: String,
)
