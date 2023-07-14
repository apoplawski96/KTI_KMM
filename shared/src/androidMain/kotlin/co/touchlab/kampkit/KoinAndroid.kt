package co.touchlab.kampkit

import android.content.Context
import com.example.myapplication.common.coroutines.DispatcherProvider
import co.touchlab.kampkit.db.KaMPKitDb
import com.example.myapplication.common.JsonFilePathRetriever
import com.example.myapplication.common.JsonFileReader
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
}

class AndroidJsonFilePathRetriever(private val context: Context) : JsonFilePathRetriever {

    override fun getPath(fileName: String): String = context.assets.open(fileName).toString()
}

fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}
