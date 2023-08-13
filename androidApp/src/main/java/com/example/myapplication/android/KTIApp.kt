package com.example.myapplication.android

import android.app.Application
import co.touchlab.kampkit.AppInfo
import co.touchlab.kampkit.initKoin
import com.example.myapplication.android.di.appModule
import com.example.myapplication.android.di.presentationModule

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            modules = arrayOf(
                presentationModule,
                appModule(this)
            )
        )
    }
}

object AndroidAppInfo : AppInfo {
    override val appId: String = "APP NAME TODO"
}
