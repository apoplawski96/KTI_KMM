package com.example.myapplication.legacy

import com.example.myapplication.model.Question
import co.apoplawski96.kti.questions.data.questions
import com.apoplawski96.killtheinterview.feature.questions.dependency.data.questionsAndroidFramework
import com.example.myapplication.data.questionsNew
import co.apoplawski96.kti.questions.data.questionsRxJava

class QuestionsRepository{

    fun getAllQuestions(): List<Question> =
        (questions + questionsNew + questionsRxJava + questionsAndroidFramework)
}