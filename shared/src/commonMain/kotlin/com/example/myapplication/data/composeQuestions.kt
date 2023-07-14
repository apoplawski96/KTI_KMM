package co.apoplawski96.kti.questions.data

import com.example.myapplication.model.DeprecatedCategory
import com.example.myapplication.model.Question

private val category = DeprecatedCategory.Compose

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