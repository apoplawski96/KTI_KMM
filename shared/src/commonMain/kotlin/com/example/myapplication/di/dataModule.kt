package com.example.myapplication.di

import com.example.myapplication._legacy.QuestionsRepository
import com.example.myapplication.data.QuestionsDataSource
import com.example.myapplication.feature.categories.data.CategoriesRepository
import com.example.myapplication.data.openAi.OpenAIPrompter
import com.example.myapplication.feature.interview.data.AIInterviewQuestionsPrompter
import com.example.myapplication.feature.subcategories.data.SubCategoriesRepository
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