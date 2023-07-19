package com.example.myapplication.data

import com.example.myapplication.QuestionSchema
import com.example.myapplication.common.JsonFileReader
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val FILE_NAME_DROID = "questions_android.json"
private const val FILE_NAME_IOS = "questions_ios.json"
private const val FILE_NAME_DESIGN_PATTERNS = "questions_design_patterns.json"
private const val FILE_NAME_KOTLIN = "questions_kotlin.json"
private const val FILE_NAME_GIT = "questions_git.json"
private const val FILE_NAME_PROGRAMMING_PARADIGMS = "questions_programming_paradigms.json"

class QuestionsDataSource(private val jsonFileReader: JsonFileReader) {

    fun getQuestionsAndroid(): List<QuestionSchema> =
        decodeQuestionsFromFile(FILE_NAME_DROID)

    fun getQuestionsIOS(): List<QuestionSchema> =
        decodeQuestionsFromFile(FILE_NAME_IOS)

    fun getQuestionsDesignPatterns(): List<QuestionSchema> =
        decodeQuestionsFromFile(FILE_NAME_DESIGN_PATTERNS)

    fun getQuestionsGit(): List<QuestionSchema> =
        decodeQuestionsFromFile(FILE_NAME_GIT)

    fun getQuestionsKotlin(): List<QuestionSchema> =
        decodeQuestionsFromFile(FILE_NAME_KOTLIN)

    fun getQuestionsProgrammingParadigms(): List<QuestionSchema> =
        decodeQuestionsFromFile(FILE_NAME_PROGRAMMING_PARADIGMS)

    private fun decodeQuestionsFromFile(fileName: String): List<QuestionSchema> {
        val jsonFileContent = jsonFileReader.readJsonFile(fileName)
        return Json.decodeFromString(jsonFileContent)
    }
}