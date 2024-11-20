package ru.hse.speakeasy.app.screen.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.hse.speakeasy.app.core.data.TranslationHistory
import ru.hse.speakeasy.app.core.data.TranslationHistoryDao
import ru.hse.speakeasy.app.core.domain.LanguageCode
import ru.hse.speakeasy.app.core.domain.history.SaveHistoryUseCase
import ru.hse.speakeasy.app.core.domain.translation.TranslationUseCase
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translationUseCase: TranslationUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val translationHistoryDao: TranslationHistoryDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(TranslationUiState())
    val uiState: StateFlow<TranslationUiState> = _uiState

    fun updateInputText(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun clearInputText() {
        _uiState.update { it.copy(inputText = "") }
    }

    fun swapLanguages() {
        _uiState.update {
            it.copy(
                sourceLang = it.targetLang,
                targetLang = it.sourceLang
            )
        }
    }

    fun translateText() {
        viewModelScope.launch {
            val result = translationUseCase.translate(
                sl = LanguageCode.fromName(_uiState.value.sourceLang),
                dl = LanguageCode.fromName(_uiState.value.targetLang),
                text = _uiState.value.inputText,
            )

            _uiState.update {
                it.copy(
                    translatedText = result.translations.possibleTranslations.firstOrNull()
                        ?: _uiState.value.inputText
                )
            }
            saveHistoryUseCase.save(_uiState.value.inputText, _uiState.value.translatedText.orEmpty())

            val translatedText = result.translations.possibleTranslations.firstOrNull() ?: ""

            val translationHistory = TranslationHistory(
                sourceText = _uiState.value.inputText,
                translatedText = translatedText
            )

            saveHistoryUseCase.save(translationHistory)

            _uiState.update {
                it.copy(
                    translatedText = translatedText,
                    currentTranslation = translationHistory,
                    isFavorite = translationHistory.isFavorite
                )
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val currentTranslation = _uiState.value.currentTranslation
            currentTranslation?.let {
                val updatedTranslation = it.copy(isFavorite = !it.isFavorite)
                translationHistoryDao.updateTranslation(updatedTranslation)
                _uiState.update { state ->
                    state.copy(
                        isFavorite = updatedTranslation.isFavorite,
                        currentTranslation = updatedTranslation
                    )
                }
            }
        }
    }
    fun updateSourceLanguage(language: String) {
        _uiState.update { it.copy(sourceLang = language) }
    }

    fun updateTargetLanguage(language: String) {
        _uiState.update { it.copy(targetLang = language) }
    }

}

data class TranslationUiState(
    val sourceLang: String = "English",
    val targetLang: String = "Russia",
    val inputText: String = "",
    val translatedText: String? = null,
    val isFavorite: Boolean = false,
    val currentTranslation: TranslationHistory? = null
)

