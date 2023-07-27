package com.example.myapplication.screens.home.domain

import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.screens.subcategories.SubCategoriesRepository

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