package org.example.project.ai

import dev.shreyaspatil.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AiService {
    suspend fun summarize(content: String): Result<String>
    suspend fun suggestTitle(content: String): Result<String>
}

class GeminiAiService(private val apiKey: String) : AiService {
    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = apiKey
    )

    override suspend fun summarize(content: String): Result<String> = withContext(Dispatchers.Default) {
        runCatching {
            val response = model.generateContent("Please summarize the following note content in a few concise sentences:\n\n$content")
            response.text ?: throw Exception("No summary generated")
        }
    }

    override suspend fun suggestTitle(content: String): Result<String> = withContext(Dispatchers.Default) {
        runCatching {
            val response = model.generateContent("Based on this note content, suggest a short, catchy title (max 5 words):\n\n$content")
            val text = response.text ?: throw Exception("No title suggested")
            text.trim().removeSurrounding("\"")
        }
    }
}
