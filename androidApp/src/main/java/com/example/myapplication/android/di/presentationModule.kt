package com.example.myapplication.android.di

import com.example.myapplication.android.screens.welcome.WelcomeScreenViewModel
import com.example.myapplication.feature.categories.CategoriesViewModel
import com.example.myapplication.feature.home.HomeScreenViewModel
import com.example.myapplication.feature.interview.AIInterviewViewModel
import com.example.myapplication.feature.list.QuestionsListViewModel
import com.example.myapplication.feature.subcategories.SubCategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::QuestionsListViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::SubCategoriesViewModel)
    viewModelOf(::WelcomeScreenViewModel)
    viewModelOf(::AIInterviewViewModel)
}