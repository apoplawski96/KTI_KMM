package com.example.myapplication.di

import com.example.myapplication.feature.list.domain.GetQuestionsList
import com.example.myapplication.feature.list.domain.QuestionsMapper
import com.example.myapplication.feature.home.domain.GetHomeScreenFeedItems
import com.example.myapplication.feature.home.domain.GetRandomSubCategories
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val domainModule = module {
    singleOf(::GetQuestionsList)
    singleOf(::QuestionsMapper)
    singleOf(::GetHomeScreenFeedItems)
    singleOf(::GetRandomSubCategories)
}