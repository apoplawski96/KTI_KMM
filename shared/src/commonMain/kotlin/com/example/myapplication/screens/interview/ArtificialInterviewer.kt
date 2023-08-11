package com.example.myapplication.screens.interview

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAI
import com.example.myapplication.data.openAi.API_KEY
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class OpenAIPrompter {

    private val openAi = OpenAI(
        token = API_KEY,
        timeout = Timeout(socket = 60.toDuration(DurationUnit.SECONDS))
    )

    suspend fun executePrompt(prompt: String): String {


        return ""
    }
}