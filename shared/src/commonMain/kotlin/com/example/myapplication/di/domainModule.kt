package com.example.myapplication.di

import com.example.myapplication.feature.list.data.GetQuestionsList
import com.example.myapplication.feature.list.data.QuestionsMapper
import com.example.myapplication.feature.home.data.GetHomeScreenFeedItems
import com.example.myapplication.feature.home.data.GetRandomSubCategories
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val domainModule = module {
    singleOf(::GetQuestionsList)
    singleOf(::QuestionsMapper)
    singleOf(::GetHomeScreenFeedItems)
    singleOf(::GetRandomSubCategories)
}