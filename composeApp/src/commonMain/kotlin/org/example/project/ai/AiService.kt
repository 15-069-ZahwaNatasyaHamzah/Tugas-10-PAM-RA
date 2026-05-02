package org.example.project.ai

import dev.shreyaspatil.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AiService {
    suspend fun summarize(content: String): Result<String>
    suspend fun suggestTitle(content: String): Result<String>
}

class GeminiAiService(private val apiKey: String) : AiService {
    // Gunakan 'gemini-pro' sebagai alternatif jika flash bermasalah dengan versi API
    private val model = GenerativeModel(
        modelName = "gemini-pro", 
        apiKey = apiKey
    )

    override suspend fun summarize(content: String): Result<String> = withContext(Dispatchers.Default) {
        runCatching {
            if (apiKey.isBlank() || apiKey.startsWith("YOUR_")) {
                throw Exception("API Key belum valid")
            }
            
            val prompt = "Ringkaslah teks berikut dalam Bahasa Indonesia yang singkat:\n\n$content"
            val response = model.generateContent(prompt)
            response.text ?: throw Exception("AI tidak memberikan respon")
        }.recover { error ->
            // Fallback jika API benar-benar gagal karena masalah versi/model
            if (error.message?.contains("v1beta") == true || error.message?.contains("not found") == true) {
                "Pesan: Ringkasan gagal karena masalah versi API Google. (Isi: ${content.take(30)}...)"
            } else {
                throw error
            }
        }
    }

    override suspend fun suggestTitle(content: String): Result<String> = withContext(Dispatchers.Default) {
        runCatching {
            val response = model.generateContent("Berikan judul singkat untuk: $content")
            response.text?.trim()?.removeSurrounding("\"") ?: "Judul Baru"
        }
    }
}
