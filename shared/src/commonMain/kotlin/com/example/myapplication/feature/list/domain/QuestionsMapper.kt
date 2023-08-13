package com.example.myapplication.feature.list.domain

import com.example.myapplication.model.data.QuestionSchema
import com.example.myapplication._legacy.DeprecatedCategory
import com.example.myapplication.model.domain.Difficulty
import com.example.myapplication.model.domain.Question
import com.example.myapplication.model.domain.SubCategory
import com.example.myapplication.model.domain.TopCategory
import com.example.myapplication.model.domain.allSubCategoriesFlatten

class QuestionsMapper {

    fun map(questions: List<QuestionSchema>): List<Question> =
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
        allSubCategoriesFlatten.firstOrNull { subCategory ->
            name == subCategory.keyName
        }
}