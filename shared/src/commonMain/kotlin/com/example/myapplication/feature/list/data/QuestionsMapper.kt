package com.example.myapplication.feature.list.data

import com.example.myapplication.model.schema.QuestionSchema
import com.example.myapplication._legacy.DeprecatedCategory
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Question
import com.example.myapplication.model.SubCategory
import com.example.myapplication.model.TopCategory
import com.example.myapplication.model.allSubCategoriesFlatten

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