package com.example.myapplication

import android.content.Context
import com.example.myapplication.common.JsonFilePathRetriever
import com.example.myapplication.common.JsonFileReader
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun parseQuestionsJson(
    fileName: String,
    jsonFileReader: JsonFileReader,
): List<MQuestion> {
    val jsonFileContent = jsonFileReader.readJsonFile(fileName)
    return Json.decodeFromString(jsonFileContent)
}

actual fun readJsonFile(fileName: String): String {
    return "huj"
}