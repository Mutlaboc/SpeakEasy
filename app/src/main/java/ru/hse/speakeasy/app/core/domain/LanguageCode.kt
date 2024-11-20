package ru.hse.speakeasy.app.core.domain

enum class LanguageCode(val code: String, val displayName: String) {
    ENGLISH("en", "English"),
    RUSSIAN("ru", "Russian"),
    SPANISH("es", "Spanish"),
    FRENCH("fr", "French"),
    GERMAN("de", "German"),
    ITALIAN("it", "Italian"),
    JAPANESE("ja", "Japanese"),
    KOREAN("ko", "Korean"),
    CHINESE("zh", "Chinese"),
    PORTUGUESE("pt", "Portuguese"),
    ARABIC("ar", "Arabic");


    companion object {
        fun fromName(name: String): LanguageCode {
            return values().firstOrNull { it.displayName == name } ?: ENGLISH
        }
    }
}