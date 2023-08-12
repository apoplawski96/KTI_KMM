package com.example.myapplication.screens.interview.domain

import com.example.myapplication.data.QuestionsDatabaseHelper
import com.example.myapplication.screens.interview.data.OpenAIPrompter
import com.example.myapplication.screens.interview.domain.model.AIQuestion
import com.example.myapplication.screens.interview.domain.model.AIQuestionSchema
import com.example.myapplication.screens.interview.domain.model.Role
import kotlinx.serialization.json.Json

class AIInterviewQuestionsPrompter(
    private val openAIPrompter: OpenAIPrompter,
    private val questionsDatabase: QuestionsDatabaseHelper,
) {

    sealed interface Result {
        data class Success(val question: AIQuestion) : Result
        object Error : Result
    }

    sealed interface ResultJson {
        data class Success(val question: AIQuestionSchema) : ResultJson
        object Error : ResultJson
    }

    private val inMemoryQuestionsCache: MutableList<AIQuestionSchema> = mutableListOf()

    suspend fun promptForQuestionJson(role: Role): ResultJson = try {
        val prompt = """
            Hello chat! I am applying for ${role.seniority} ${role.roleType} position and I am preparing for a technical interview.
            Act as an interviewer and ask me a question that is relevant for my position and seniority.
            Ask only concrete, technical questions. 
            Try to really role-play an interviewer.
            Generate answer to the question.
            Do not repeat questions, that are already in cache, cache: ${inMemoryQuestionsCache.map { questionSchema -> questionSchema.question }}
            Ask only about modern technologies, do not use outdated and old sources.
            Return question and answer as JSON object. 
            Return JSON and nothing else.
            Example structure for the JSON object:
              {
                "question": "What are some use cases for implementing test modules in Android apps?",
                "answer": "Test modules are useful for sharing test code across multiple modules, maintaining cleaner build configurations, implementing integration tests, and organizing large-scale applications with complex codebases."
              },
        """.trimIndent()

        val promptResponse = openAIPrompter.executePrompt(prompt)

        if (promptResponse.isNullOrEmpty() || promptResponse.isBlank()) {
            ResultJson.Error
        } else {
            val question: AIQuestionSchema = Json.decodeFromString(promptResponse)

            questionsDatabase.insertQuestions(listOf(question))
            inMemoryQuestionsCache.add(question)

            ResultJson.Success(question = question)
        }
    } catch (e: Exception) {
        println("2137 - exception: $e")
        ResultJson.Error
    }

//    suspend fun promptForQuestion(role: Role): Result = try {
//        val prompt = """
//            Hello chat! I am applying for ${role.seniority} ${role.roleType} position and I am preparing for a technical interview.
//            Act as an interviewer and ask me a question that is relevant for my position and seniority.
//            Try to really role-play an interviewer.
//            Don't generate answers.
//        """.trimIndent()
//
//        val promptResponse = openAIPrompter.executePrompt(prompt)
//
//        if (promptResponse.isNullOrEmpty() || promptResponse.isBlank()) {
//            Result.Error
//        } else {
//            Result.Success(question = AIQuestion(content = promptResponse))
//        }
//    } catch (e: Exception) {
//        println("2137 - exception: $e")
//        Result.Error
//    }
}