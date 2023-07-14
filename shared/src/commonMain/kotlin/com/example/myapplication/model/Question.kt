package com.example.myapplication.model

import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.AndroidSubCategory
import com.example.myapplication.model.subcategory.TopCategory

data class Question(
    val category: DeprecatedCategory,
    val topCategory: TopCategory = TopCategory.ANDROID,
    val subCategory: SubCategory = AndroidSubCategory.Basics,
    val difficulty: Difficulty = Difficulty.Beginner,
    val question: String,
    val answer: String,
)

val question1 = Question(
    topCategory = TopCategory.ANDROID,
    subCategory = AndroidSubCategory.Compose,
    difficulty = Difficulty.Advanced,
    question = "hello",
    answer = "hello2",
    category = DeprecatedCategory.Android
)
