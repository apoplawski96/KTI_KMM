package com.example.myapplication.screens.home.domain

import com.example.myapplication.screens.home.HomeScreenFeedItem
import com.example.myapplication.screens.home.HomeScreenMenuItem

class GetHomeScreenFeedItems {

    suspend fun get(): List<HomeScreenFeedItem> = listOf(
        HomeScreenFeedItem.MenuItems(
            items = listOf(
                HomeScreenMenuItem.QUESTIONS_QUIZ,
                HomeScreenMenuItem.QUESTIONS_CATEGORIES
            )
        )
    )
}