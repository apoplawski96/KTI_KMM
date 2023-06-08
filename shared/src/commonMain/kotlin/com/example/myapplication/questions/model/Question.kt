package co.apoplawski96.kti.questions.model

import co.apoplawski96.kti.questions.model.subcategory.SubCategory
import co.apoplawski96.kti.questions.model.subcategory.android.AndroidSubCategory
import co.touchlab.kampkit.domain.model.Difficulty

data class Question(
    val category: Category,
    val subCategory: SubCategory = AndroidSubCategory.Activities,
    val difficulty: Difficulty = Difficulty.Beginner,
    val question: String,
    val title: String = question,
    val answer: String,
)
