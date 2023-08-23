package com.example.myapplication.di

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.myapplication.screens.categories.CategoriesScreenModel
import com.example.myapplication.screens.home.HomeScreenModel
import com.example.myapplication.screens.interview.AIInterviewScreenModel
import com.example.myapplication.screens.list.QuestionsListScreenModel
import com.example.myapplication.screens.subcategories.SubCategoriesScreenModel
import com.example.myapplication.screens.welcome.WelcomeScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin

@Composable
inline fun <reified T : ScreenModel> Screen.getScreenModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val koin = getKoin()
    return rememberScreenModel(tag = qualifier?.value) { koin.get(qualifier, parameters) }
}

val screenModelModule = module {
    factoryOf(::WelcomeScreenModel)
    factoryOf(::AIInterviewScreenModel)
    factoryOf(::HomeScreenModel)
    factoryOf(::SubCategoriesScreenModel)
    factoryOf(::QuestionsListScreenModel)
    factoryOf(::CategoriesScreenModel)
}