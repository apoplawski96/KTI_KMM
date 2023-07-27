package com.example.myapplication.domain

import com.example.myapplication.data.QuestionsDataSource
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory

class GetQuestions(
    private val questionsDataSource: QuestionsDataSource,
    private val questionsMapper: QuestionsMapper,
) {

    sealed interface Result {
        data class Success(val questions: List<Question>) : Result
        object Error : Result
    }

    fun invoke(topCategory: TopCategory, subCategory: SubCategory?): Result = try {
        val questionsRaw = when(topCategory) {
            TopCategory.ANDROID -> questionsDataSource.getQuestionsAndroid()
            TopCategory.GIT -> questionsDataSource.getQuestionsGit()
            TopCategory.REST -> emptyList()
            TopCategory.DESIGN_PATTERNS -> questionsDataSource.getQuestionsDesignPatterns()
            TopCategory.PROGRAMMING_PARADIGMS -> questionsDataSource.getQuestionsProgrammingParadigms()
            TopCategory.KOTLIN -> questionsDataSource.getQuestionsKotlin()
        }
        val questionsConverted = questionsMapper.map(questionsRaw)

        val result = if (subCategory != null) {
            questionsConverted.filter { question ->
                question.subCategory == subCategory
            }
        } else {
            questionsConverted
        }

        Result.Success(questions = result)
    } catch (e: Exception) {
        Result.Error
    }
}