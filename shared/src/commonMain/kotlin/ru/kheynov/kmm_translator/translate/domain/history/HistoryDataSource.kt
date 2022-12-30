package ru.kheynov.kmm_translator.translate.domain.history

import ru.kheynov.kmm_translator.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    
    suspend fun insertHistoryItem(item: HistoryItem)
}