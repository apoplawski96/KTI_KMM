package com.example.myapplication.questions.domain.new

import co.apoplawski96.kti.questions.data.questionsNew
import co.apoplawski96.kti.questions.model.Question
import com.example.myapplication.questions.model.subcategory.SubCategory
import com.example.myapplication.questions.model.subcategory.TopCategory

class NewQuestionsRepository {

    fun getQuestions(topCategory: TopCategory, subCategory: SubCategory): List<Question> =
        when(topCategory) {
            TopCategory.ANDROID -> questionsNew.filter { question -> question.subCategory == subCategory }
            TopCategory.IOS -> questionsNew.filter { question -> question.subCategory == subCategory }
        }
}