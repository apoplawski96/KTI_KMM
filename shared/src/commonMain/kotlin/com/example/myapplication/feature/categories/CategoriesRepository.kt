package com.example.myapplication.feature.categories

import com.example.myapplication.model.domain.TopCategory

class CategoriesRepository {

    fun getTopCategories(): List<TopCategory> = TopCategory.values().toList()
}