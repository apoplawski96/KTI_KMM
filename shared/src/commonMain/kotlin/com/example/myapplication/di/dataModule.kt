package com.example.myapplication.di

import com.example.myapplication.data.QuestionsDataSource
import com.example.myapplication.legacy.QuestionsRepository
import com.example.myapplication.screens.categories.CategoriesRepository
import com.example.myapplication.screens.interview.data.OpenAIPrompter
import com.example.myapplication.screens.interview.domain.AIInterviewQuestionsRepository
import com.example.myapplication.screens.subcategories.SubCategoriesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::QuestionsDataSource)
    singleOf(::SubCategoriesRepository)
    singleOf(::CategoriesRepository)
    singleOf(::QuestionsRepository)
    singleOf(::OpenAIPrompter)
    singleOf(::AIInterviewQuestionsRepository)
}