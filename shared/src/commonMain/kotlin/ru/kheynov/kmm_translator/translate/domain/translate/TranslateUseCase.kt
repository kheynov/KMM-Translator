package ru.kheynov.kmm_translator.translate.domain.translate

import ru.kheynov.kmm_translator.core.domain.language.Language
import ru.kheynov.kmm_translator.core.domain.util.Resource
import ru.kheynov.kmm_translator.translate.domain.history.HistoryDataSource
import ru.kheynov.kmm_translator.translate.domain.history.HistoryItem

class TranslateUseCase(
    private val client: TranslateClient, private val historyDataSource: HistoryDataSource
) {
    //TODO: Try to change to override invoke
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): Resource<String> = try {
        val translatedText = client.translate(
            fromLanguage, fromText, toLanguage
        )
        historyDataSource.insertHistoryItem(
            HistoryItem(
                id = null,
                fromLanguageCode = fromLanguage.langCode,
                toLanguageCode = toLanguage.langCode,
                fromText = fromText,
                toText = translatedText,
            )
        )
        Resource.Success(translatedText)
    } catch (e: TranslateException) {
        e.printStackTrace()
        Resource.Error(e)
    }
}
