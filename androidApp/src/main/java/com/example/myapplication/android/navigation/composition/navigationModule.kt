package com.example.myapplication.android.navigation.composition

import co.apoplawski96.kti.common.coroutines.DispatcherProvider
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.android.navigation.KTINavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val navigationModule = module {
    single<Navigator> {
        val dispatcherProvider: DispatcherProvider = get()
        val scope = CoroutineScope(SupervisorJob() + dispatcherProvider.main)
        KTINavigator(scope)
    }
}
