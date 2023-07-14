package com.example.myapplication.android.navigation.composition

import com.example.myapplication.common.coroutines.DispatcherProvider
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
