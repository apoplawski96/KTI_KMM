package com.example.myapplication.feature.home.data

import com.example.myapplication.model.HomeScreenFeedItem
import com.example.myapplication.model.HomeScreenMenuItem

class GetHomeScreenFeedItems(private val getRandomSubCategories: GetRandomSubCategories) {

    suspend fun get(): List<HomeScreenFeedItem> {
        val randomSubCategoriesCarousel = getRandomSubCategories.invoke()

        return listOf(
            HomeScreenFeedItem.MenuItems(
                items = listOf(
                    HomeScreenMenuItem.AI_INTERVIEW,
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