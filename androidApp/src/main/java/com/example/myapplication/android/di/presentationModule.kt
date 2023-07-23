package com.example.myapplication.android.di

import com.example.myapplication.android.ui.welcome.WelcomeScreenViewModel
import com.example.myapplication.screens.categories.CategoriesViewModel
import com.example.myapplication.screens.home.HomeScreenViewModel
import com.example.myapplication.screens.list.ListViewModel
import com.example.myapplication.screens.subcategories.SubCategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::ListViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::SubCategoriesViewModel)
    viewModelOf(::WelcomeScreenViewModel)
}