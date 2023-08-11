package com.example.myapplication.screens.interview.domain.model

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
value class AIQuestion(val content: String)

@Serializable
data class AIQuestionSchema(
    val question: String,
    val answer: String,
)