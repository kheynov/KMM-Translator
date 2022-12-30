package ru.kheynov.kmm_translator.translate.data.history

import database.HistoryEntity
import ru.kheynov.kmm_translator.translate.domain.history.HistoryItem

fun HistoryEntity.toHistoryItem(): HistoryItem {
    return HistoryItem(
        id = id,
        fromLanguageCode = fromLanguageCode,
        toLanguageCode = toLanguageCode,
        fromText = fromText,
        toText = toText,
    )
}