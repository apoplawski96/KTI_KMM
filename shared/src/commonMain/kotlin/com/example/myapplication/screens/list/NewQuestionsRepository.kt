package com.example.myapplication.screens.list

import com.example.myapplication.data.questionsNew
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.Random
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory

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