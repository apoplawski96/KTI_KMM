package com.example.myapplication.screens.home.domain

import com.example.myapplication.screens.home.HomeScreenFeedItem
import com.example.myapplication.screens.home.HomeScreenMenuItem

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