package com.example.myapplication.domain

import com.example.myapplication.data.model.QuestionSchema
import com.example.myapplication.model.DeprecatedCategory
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.model.subcategory.allSubCategories

class QuestionsConverter {

    fun convert(questions: List<QuestionSchema>): List<Question> =
        questions.mapNotNull { questionSchema ->
            Question(
                id = questionSchema.id,
                answer = questionSchema.answer,
                question = questionSchema.question,
                category = DeprecatedCategory.Android,
                difficulty = Difficulty.getForName(questionSchema.difficulty) ?: Difficulty.Intermediate,
                topCategory = TopCategory.getForName(questionSchema.topCategory) ?: return@mapNotNull null,
                subCategory = getSubCategoryForName(questionSchema.subCategory),
                topCategoryId = questionSchema.topCategoryId,
                subCategoryId = questionSchema.subCategoryId,
            )
        }

    private fun getSubCategoryForName(name: String): SubCategory? =
        allSubCategories.firstOrNull { subCategory ->
            name == subCategory.keyName
        }
}