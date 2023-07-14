package com.example.myapplication

import com.example.myapplication.common.JsonFileReader

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

//expect class JsonFileReader {
//    fun readJsonFile(filePath: String): String
//}

expect fun readJsonFile(fileName: String): String

expect fun parseQuestionsJson(
    fileName: String,
    jsonFileReader: JsonFileReader
): List<QuestionSchema>
