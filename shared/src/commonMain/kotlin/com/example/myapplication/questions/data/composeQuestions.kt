package co.apoplawski96.kti.questions.data

import co.apoplawski96.kti.questions.model.Category
import co.apoplawski96.kti.questions.model.Question

private val category = Category.Compose

internal val composeQuestions: List<Question> = listOf(
    Question(
        category = category,
        question = "Compose: steps to improve performance",
        answer = """
            - Build in release mode and use R8
            - Use a baseline profile
            - Use remember to minimize calculations
            - Use lazy layout keys
            - Use derivedStateOf to limit recompositions
            - Defer reads as long as possible
            - 
        """.trimIndent()
    ),
)