package com.example.myapplication.di

import com.example.myapplication.domain.GetQuestions
import com.example.myapplication.domain.QuestionsMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val domainModule = module {
    singleOf(::GetQuestions)
    singleOf(::QuestionsMapper)
}