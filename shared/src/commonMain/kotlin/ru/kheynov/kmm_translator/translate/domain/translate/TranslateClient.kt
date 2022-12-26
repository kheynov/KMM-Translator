package ru.kheynov.kmm_translator.translate.domain.translate

import ru.kheynov.kmm_translator.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): String
}