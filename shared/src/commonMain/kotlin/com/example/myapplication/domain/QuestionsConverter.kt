package com.example.myapplication.domain

import com.example.myapplication.QuestionSchema
import com.example.myapplication.model.DeprecatedCategory
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.model.subcategory.allSubCategories

class QuestionsConverter {

    fun convert(questions: List<QuestionSchema>): List<Question> =
        questions.map { questionSchema ->
            Question(
                id = questionSchema.id,
                answer = questionSchema.answer,
                question = questionSchema.question,
                category = DeprecatedCategory.Android, // todo: fix emptyList returns
                difficulty = Difficulty.getForName(questionSchema.difficulty) ?: return emptyList(),
                topCategory = TopCategory.getForName(questionSchema.topCategory) ?: return emptyList(),
                subCategory = getSubCategoryForName(questionSchema.subCategory) ?: return emptyList(),
                topCategoryId = questionSchema.topCategoryId,
                subCategoryId = questionSchema.subCategoryId,
            )
        }

    private fun getSubCategoryForName(name: String): SubCategory? =
        allSubCategories.firstOrNull { subCategory ->
            name == subCategory.displayName
        }
}