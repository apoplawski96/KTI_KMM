package com.example.myapplication.feature.home.domain

import com.example.myapplication.model.domain.SubCategory
import com.example.myapplication.model.domain.TopCategory
import com.example.myapplication.feature.subcategories.SubCategoriesRepository

class GetRandomSubCategories(private val subCategoriesRepository: SubCategoriesRepository) {

    data class Result(
        val topCategory: TopCategory,
        val subCategories: List<SubCategory>
    )

    fun invoke(): Result {
        val randomTopCategory =
            TopCategory.values().filter { topCategory ->
                topCategory.subCategories.isNotEmpty()
            }.random()

        val subCategoriesForCategory =
            subCategoriesRepository.getSubCategories(randomTopCategory).shuffled()

        return Result(
            topCategory = randomTopCategory,
            subCategories = subCategoriesForCategory,
        )
    }
}