package com.example.myapplication.feature.categories.data

import com.example.myapplication.model.TopCategory

class CategoriesRepository {

    fun getTopCategories(): List<TopCategory> = TopCategory.values().toList()
}