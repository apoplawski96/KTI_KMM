package com.example.myapplication.di

import com.example.myapplication._legacy.QuestionsRepository
import com.example.myapplication.data.QuestionsDataSource
import com.example.myapplication.feature.categories.CategoriesRepository
import com.example.myapplication.data.OpenAIPrompter
import com.example.myapplication.feature.interview.domain.AIInterviewQuestionsPrompter
import com.example.myapplication.feature.subcategories.SubCategoriesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::QuestionsDataSource)
    singleOf(::SubCategoriesRepository)
    singleOf(::CategoriesRepository)
    singleOf(::QuestionsRepository)
    singleOf(::OpenAIPrompter)
    singleOf(::AIInterviewQuestionsPrompter)
}