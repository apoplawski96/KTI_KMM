package com.example.myapplication.data

import co.touchlab.kampkit.db.KaMPKitDb
import co.touchlab.kampkit.db.Question
import co.touchlab.kampkit.sqldelight.transactionWithContext
import com.example.myapplication.model.schema.AIQuestionSchema
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class QuestionsDatabaseHelper(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {

    private val dbRef: KaMPKitDb = KaMPKitDb(sqlDriver)

    fun selectAllItems(): Flow<List<Question>> =
        dbRef.questionsQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun insertQuestions(questions: List<AIQuestionSchema>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            questions.forEach { question ->
                dbRef.questionsQueries.insertQuestion(
                    questionText = question.question,
                    answer = question.answer
                )
            }
        }
    }

    fun selectById(id: Long): Flow<List<Question>> =
        dbRef.questionsQueries
            .selectById(id)
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun deleteAll() {
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.questionsQueries.deleteAll()
        }
    }
}
