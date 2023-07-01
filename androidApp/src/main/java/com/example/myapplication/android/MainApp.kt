package com.example.myapplication.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import co.apoplawski96.kti.common.coroutines.DispatcherProvider
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.questions.domain.legacy.QuestionsRepository
import co.apoplawski96.kti.questions.domain.interactors.GetQuestionsShuffled
import com.example.myapplication.questions.view.HomeScreenViewModel
import com.example.myapplication.questions.view.ListViewModel
import co.touchlab.kampkit.AppInfo
import co.touchlab.kampkit.initKoin
import co.touchlab.kampkit.models.BreedViewModel
import com.example.myapplication.android.navigation.KTINavigator
import com.example.myapplication.android.ui.subcategory.SubCategoriesViewModel
import com.example.myapplication.domain.SubCategoriesRepository
import com.example.myapplication.questions.domain.new.NewQuestionsRepository
import com.example.myapplication.questions.view.CategoriesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin( appModule =
            module {
                single<Context> { this@MainApp }
                viewModel { BreedViewModel(get(), get { parametersOf("BreedViewModel") }) }
                viewModelOf(::HomeScreenViewModel)
                viewModelOf(::ListViewModel)
                viewModelOf(::CategoriesViewModel)
                viewModelOf(::SubCategoriesViewModel)
                singleOf(::SubCategoriesRepository)
                singleOf(::GetQuestionsShuffled)
                singleOf(::QuestionsRepository)
                singleOf(::NewQuestionsRepository)
                single<SharedPreferences> {
                    get<Context>().getSharedPreferences("KAMPSTARTER_SETTINGS", Context.MODE_PRIVATE)
                }
                single<AppInfo> { AndroidAppInfo }
                single {
                    { Log.i("Startup", "Hello from Android/Kotlin!") }
                }
                single<Navigator> {
                    val dispatcherProvider: DispatcherProvider = get()
                    val scope = CoroutineScope(SupervisorJob() + dispatcherProvider.main)
                    KTINavigator(scope)
                }
            }
        )
    }
}

object AndroidAppInfo : AppInfo {
    override val appId: String = "APP NAME TODO"
}