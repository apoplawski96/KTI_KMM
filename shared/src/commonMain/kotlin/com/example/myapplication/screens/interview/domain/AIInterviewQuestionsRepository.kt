package com.example.myapplication.screens.interview.domain

import com.example.myapplication.screens.interview.data.OpenAIPrompter
import com.example.myapplication.screens.interview.domain.model.AIQuestion
import com.example.myapplication.screens.interview.domain.model.Role

class AIInterviewQuestionsRepository(
    private val openAIPrompter: OpenAIPrompter
) {

    sealed interface Result {
        data class Success(val question: AIQuestion) : Result
        object Error : Result
    }

    suspend fun promptForQuestion(role: Role): Result = try {
        val prompt = """
            Hello chat! I am applying for ${role.seniority} ${role.roleType} position and I am preparing for a technical interview.
            Act as an interviewer and ask me a question that is relevant for my position and seniority.
            Try to really role-play an interviewer.
            Don't generate answers.
        """.trimIndent()

        val promptResponse = openAIPrompter.executePrompt(prompt)

        if (promptResponse.isNullOrEmpty() || promptResponse.isBlank()) {
            Result.Error
        } else {
            Result.Success(question = AIQuestion(content = promptResponse))
        }
    } catch (e: Exception) {
        println("2137 - exception: $e")
        Result.Error
    }
}