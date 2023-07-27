package com.example.myapplication.android.ui.home

import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory

sealed class HomeScreenFeedItem {
    data class LastLearnedSubCategory(
        val subCategory: SubCategory
    ) : HomeScreenFeedItem()
    data class LastLearnedSubCategoriesCarousel(
        val subCategories: List<SubCategory>,
    ) : HomeScreenFeedItem()
    data class RandomSubCategoriesCarousel(
        val subCategories: List<SubCategory>,
        val category: TopCategory
    ) : HomeScreenFeedItem()
    data class RandomBookmarkedQuestion(
        val question: Question
    ) : HomeScreenFeedItem()
}
