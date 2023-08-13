package com.example.myapplication.feature.home.domain

import com.example.myapplication.model.domain.HomeScreenFeedItem
import com.example.myapplication.model.domain.HomeScreenMenuItem

class GetHomeScreenFeedItems(private val getRandomSubCategories: GetRandomSubCategories) {

    suspend fun get(): List<HomeScreenFeedItem> {
        val randomSubCategoriesCarousel = getRandomSubCategories.invoke()

        return listOf(
            HomeScreenFeedItem.MenuItems(
                items = listOf(
                    HomeScreenMenuItem.QUESTIONS_QUIZ,
                    HomeScreenMenuItem.QUESTIONS_CATEGORIES
                )
            ),
            HomeScreenFeedItem.RandomSubCategoriesCarousel(
                subCategories = randomSubCategoriesCarousel.subCategories,
                topCategory = randomSubCategoriesCarousel.topCategory,
            )
        )
    }
}