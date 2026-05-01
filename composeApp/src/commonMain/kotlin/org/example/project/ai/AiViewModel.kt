package org.example.project.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AiUiState(
    val summary: String? = null,
    val suggestedTitle: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AiViewModel(private val aiService: AiService) : ViewModel() {
    private val _uiState = MutableStateFlow(AiUiState())
    val uiState: StateFlow<AiUiState> = _uiState.asStateFlow()

    fun summarize(content: String) {
        if (content.isBlank()) {
            _uiState.update { it.copy(error = "Note content is empty") }
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, summary = null) }
            aiService.summarize(content)
                .onSuccess { summary ->
                    _uiState.update { it.copy(summary = summary, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message ?: "Failed to summarize", isLoading = false) }
                }
        }
    }

    fun suggestTitle(content: String) {
        if (content.isBlank()) {
            _uiState.update { it.copy(error = "Note content is empty") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, suggestedTitle = null) }
            aiService.suggestTitle(content)
                .onSuccess { title ->
                    _uiState.update { it.copy(suggestedTitle = title, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message ?: "Failed to suggest title", isLoading = false) }
                }
        }
    }
    
    fun clearState() {
        _uiState.update { AiUiState() }
    }
}
