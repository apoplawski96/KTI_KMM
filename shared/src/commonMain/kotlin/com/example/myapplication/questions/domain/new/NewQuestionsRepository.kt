package com.example.myapplication.questions.domain.new

import com.example.myapplication.questions.data.questionsNew
import com.example.myapplication.questions.model.Question
import com.example.myapplication.questions.model.subcategory.Random
import com.example.myapplication.questions.model.subcategory.SubCategory
import com.example.myapplication.questions.model.subcategory.TopCategory

class NewQuestionsRepository {

    fun getQuestions(topCategory: TopCategory, subCategory: SubCategory): List<Question> =
        when(topCategory) {
            TopCategory.ANDROID -> {
                if (subCategory is Random){
                    questionsNew
                } else {
                    questionsNew.filter { question -> question.subCategory == subCategory }
                }
            }
            TopCategory.IOS -> questionsNew.filter {
                    question -> question.subCategory == subCategory
            }
        }
}