package com.example.myapplication.feature.home.data

import com.example.myapplication.model.SubCategory
import com.example.myapplication.model.TopCategory
import com.example.myapplication.feature.subcategories.data.SubCategoriesRepository

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