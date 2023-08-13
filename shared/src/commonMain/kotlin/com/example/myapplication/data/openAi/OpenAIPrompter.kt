package com.example.myapplication.data.openAi

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class OpenAIPrompter {

    private val openAi = OpenAI(
        token = API_KEY,
        timeout = Timeout(socket = 60.toDuration(DurationUnit.SECONDS))
    )

    @OptIn(BetaOpenAI::class)
    suspend fun executePrompt(prompt: String): String? {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = prompt.trimIndent()
                )
            )
        )
        val completion = openAi.chatCompletion(chatCompletionRequest)

        return completion.choices[0].message?.content
    }
}