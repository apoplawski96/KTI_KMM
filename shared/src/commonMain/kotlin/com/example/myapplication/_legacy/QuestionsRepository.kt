package com.example.myapplication._legacy

import com.example.myapplication._legacy.questions.questions
import com.example.myapplication._legacy.questions.questionsAndroidFramework
import com.example.myapplication._legacy.questions.questionsNew
import com.example.myapplication._legacy.questions.questionsRxJava
import com.example.myapplication.model.Question

class QuestionsRepository{

    fun getAllQuestions(): List<Question> =
        (questions + questionsNew + questionsRxJava + questionsAndroidFramework)
}