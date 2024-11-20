package ru.hse.speakeasy.app.screen.favorite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import ru.hse.speakeasy.app.core.data.TranslationHistory
import ru.hse.speakeasy.app.core.data.TranslationHistoryDao

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao
) : ViewModel() {
    val favoriteTranslations: Flow<List<TranslationHistory>> = translationHistoryDao.getFavoriteTranslations()
}
