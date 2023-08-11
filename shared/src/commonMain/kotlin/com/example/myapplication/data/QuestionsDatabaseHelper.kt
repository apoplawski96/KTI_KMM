package com.example.myapplication.data

import co.touchlab.kampkit.db.KaMPKitDb
import co.touchlab.kampkit.db.Question
import co.touchlab.kampkit.sqldelight.transactionWithContext
import co.touchlab.kermit.Logger
import com.example.myapplication.screens.interview.domain.model.AIQuestionSchema
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class QuestionsDatabaseHelper(
    sqlDriver: SqlDriver,
    private val log: Logger,
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
        log.d { "Inserting ${questions.size} breeds into database" }
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
        log.i { "Database Cleared" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.questionsQueries.deleteAll()
        }
    }

//    suspend fun updateFavorite(breedId: Long, favorite: Boolean) {
//        log.i { "Breed $breedId: Favorited $favorite" }
//        dbRef.transactionWithContext(backgroundDispatcher) {
//            dbRef.tableQueries.updateFavorite(favorite, breedId)
//        }
//    }
}
