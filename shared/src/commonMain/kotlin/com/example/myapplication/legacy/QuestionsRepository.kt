package com.example.myapplication.legacy

import com.example.myapplication.model.Question
import com.example.myapplication.data.questions
import com.example.myapplication.data.questionsAndroidFramework
import com.example.myapplication.data.questionsNew
import com.example.myapplication.data.questionsRxJava

class QuestionsRepository{

    fun getAllQuestions(): List<Question> =
        (questions + questionsNew + questionsRxJava + questionsAndroidFramework)
}