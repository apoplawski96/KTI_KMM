package com.example.myapplication.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionSchema(
    val id: Int,
    val question: String,
    val answer: String,
    val difficulty: String,
    @SerialName("topCategory")
    val topCategory: String,
    @SerialName("topCategoryId")
    val topCategoryId: Int,
    @SerialName("subCategory")
    val subCategory: String,
    @SerialName("subCategoryId")
    val subCategoryId: Int
)