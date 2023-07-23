package co.touchlab.kampkit

import co.touchlab.kampkit.db.KaMPKitDb
import com.example.myapplication.common.JsonFilePathRetriever
import com.example.myapplication.common.JsonFileReader
import com.example.myapplication.common.coroutines.DispatcherProvider
import com.example.myapplication.coroutines.AndroidDispatcherProvider
import com.example.myapplication.json.AndroidJsonFilePathRetriever
import com.example.myapplication.json.AndroidJsonFileReader
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = KaMPKitDb.Schema,
            context = get(),
            name = "KampkitDb"
        )
    }

    single<Settings> {
        SharedPreferencesSettings(get())
    }

    single {
        OkHttp.create()
    }

    single { AndroidDispatcherProvider() } bind DispatcherProvider::class

    single<JsonFilePathRetriever> { AndroidJsonFilePathRetriever(context = get()) }
    single<JsonFileReader> { AndroidJsonFileReader(context = get()) }
}