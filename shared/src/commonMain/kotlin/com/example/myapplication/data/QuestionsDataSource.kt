package com.example.myapplication.data

import com.example.myapplication.QuestionSchema
import com.example.myapplication.common.JsonFileReader
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val FILE_NAME_DROID = "questions_android.json"
private const val FILE_NAME_IOS = "questions_ios.json"

class QuestionsDataSource(private val jsonFileReader: JsonFileReader) {

    fun getQuestionsAndroid(): List<QuestionSchema> = decodeQuestionsFromFile(FILE_NAME_DROID)

    fun getQuestionsIOS(): List<QuestionSchema> = decodeQuestionsFromFile(FILE_NAME_IOS)

    private fun decodeQuestionsFromFile(fileName: String): List<QuestionSchema> {
        val jsonFileContent = jsonFileReader.readJsonFile(fileName)
        return Json.decodeFromString(jsonFileContent)
    }
}