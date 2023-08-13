package com.example.myapplication.feature.list.domain

import com.example.myapplication.data.QuestionsDataSource
import com.example.myapplication.model.domain.Question
import com.example.myapplication.model.domain.SubCategory
import com.example.myapplication.model.domain.TopCategory

class GetQuestionsList(
    private val questionsDataSource: QuestionsDataSource,
    private val questionsMapper: QuestionsMapper,
) {

    sealed interface Result {
        data class Success(val questions: List<Question>) : Result
        object Error : Result
    }

    operator fun invoke(topCategory: TopCategory, subCategory: SubCategory?): Result = try {
        val questionsRaw = when(topCategory) {
            TopCategory.ANDROID -> questionsDataSource.getQuestionsAndroid()
            TopCategory.GIT -> questionsDataSource.getQuestionsGit()
            TopCategory.REST -> emptyList()
            TopCategory.DESIGN_PATTERNS -> questionsDataSource.getQuestionsDesignPatterns()
            TopCategory.PROGRAMMING_PARADIGMS -> questionsDataSource.getQuestionsProgrammingParadigms()
            TopCategory.KOTLIN -> questionsDataSource.getQuestionsKotlin()
            TopCategory.IOS -> questionsDataSource.getQuestionsIOS()
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