package ru.hse.speakeasy.app.core.domain

import jakarta.inject.Inject
import ru.hse.speakeasy.app.core.TranslationApi

class TranslationUseCase @Inject constructor(
    private val translationApi: TranslationApi
) {
    suspend fun translate() {

    }
}