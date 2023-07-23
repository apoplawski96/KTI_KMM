package com.example.myapplication.legacy

import com.example.myapplication.data.questions.questions
import com.example.myapplication.data.questions.questionsAndroidFramework
import com.example.myapplication.data.questions.questionsNew
import com.example.myapplication.data.questions.questionsRxJava
import com.example.myapplication.model.Question

class QuestionsRepository{

    fun getAllQuestions(): List<Question> =
        (questions + questionsNew + questionsRxJava + questionsAndroidFramework)
}