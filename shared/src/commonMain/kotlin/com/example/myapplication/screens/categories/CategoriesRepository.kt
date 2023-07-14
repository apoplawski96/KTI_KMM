package com.example.myapplication.screens.categories

import com.example.myapplication.model.subcategory.TopCategory

class CategoriesRepository {

    fun getTopCategories(): List<TopCategory> = TopCategory.values().toList()
}