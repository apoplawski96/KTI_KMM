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
    pathRetriever: JsonFilePathRetriever,
    jsonFileReader: JsonFileReader,
): List<MQuestion> {
    val filePath = pathRetriever.getPath(fileName = fileName)
    println("2137 - fileName: $fileName, filePath: $filePath")
    val jsonFileContent = jsonFileReader.readJsonFile("questions.json")
    println("2137 - jsonFileContent: $jsonFileContent")
    return Json.decodeFromString<List<MQuestion>>(jsonFileContent)
}

actual fun readJsonFile(fileName: String): String {
    return "huj"
}