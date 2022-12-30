package ru.kheynov.kmm_translator.core.presentation

import ru.kheynov.kmm_translator.core.domain.language.Language

expect class UiLanguage {
    val language: Language
    
    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}