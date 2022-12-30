package ru.kheynov.kmm_translator.translate.presentation

import ru.kheynov.kmm_translator.core.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage,
)