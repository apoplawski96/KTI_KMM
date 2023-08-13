package com.example.myapplication.data

import com.example.myapplication.common.JsonFileReader
import com.example.myapplication.model.schema.QuestionSchema
import kotlinx.serialization.json.Json

private const val FILE_NAME_DROID = "questions_android.json"
private const val FILE_NAME_DROID_FLOW = "questions_android_flow.json"
private const val FILE_NAME_DROID_COROUTINES = "questions_android_coroutines.json"
private const val FILE_NAME_DROID_LIFECYCLE = "questions_android_lifecycle.json"
private const val FILE_NAME_DROID_SECURITY = "questions_android_security.json"
private const val FILE_NAME_DROID_ARCHITECTURE = "questions_android_architecture.json"
private const val FILE_NAME_DROID_CONFIGURATION = "questions_android_configuration.json"
private const val FILE_NAME_DROID_VIEW_MODEL = "questions_android_viewmodel.json"
private const val FILE_NAME_DROID_CORE = "questions_android_core.json"
private const val FILE_NAME_DROID_COMPOSE = "questions_android_compose.json"

private const val FILE_NAME_IOS = "questions_ios.json"
private const val FILE_NAME_DESIGN_PATTERNS = "questions_design_patterns.json"
private const val FILE_NAME_KOTLIN = "questions_kotlin.json"
private const val FILE_NAME_GIT = "questions_git.json"
private const val FILE_NAME_PROGRAMMING_PARADIGMS = "questions_programming_paradigms.json"

class QuestionsDataSource(private val jsonFileReader: JsonFileReader) {

    fun getQuestionsAndroid(): List<QuestionSchema> = decodeQuestionsFromFile(FILE_NAME_DROID) +
            decodeQuestionsFromFile(FILE_NAME_DROID_FLOW) +
            decodeQuestionsFromFile(FILE_NAME_DROID_COROUTINES) +
            decodeQuestionsFromFile(FILE_NAME_DROID_LIFECYCLE) +
            decodeQuestionsFromFile(FILE_NAME_DROID_SECURITY) +
            decodeQuestionsFromFile(FILE_NAME_DROID_ARCHITECTURE) +
            decodeQuestionsFromFile(FILE_NAME_DROID_CONFIGURATION) +
            decodeQuestionsFromFile(FILE_NAME_DROID_VIEW_MODEL) +
            decodeQuestionsFromFile(FILE_NAME_DROID_CORE) +
            decodeQuestionsFromFile(FILE_NAME_DROID_COMPOSE)

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