package ru.kheynov.kmm_translator.translate.data.history

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import ru.kheynov.kmm_translator.core.domain.util.CommonFlow
import ru.kheynov.kmm_translator.core.domain.util.toCommonFlow
import ru.kheynov.kmm_translator.database.TranslateDatabase
import ru.kheynov.kmm_translator.translate.domain.history.HistoryDataSource
import ru.kheynov.kmm_translator.translate.domain.history.HistoryItem

class SqlDelightHistoryDataSource(
    db: TranslateDatabase,
) : HistoryDataSource {
    
    private val queries = db.translateQueries
    
    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries.getHistory().asFlow().mapToList().map { history ->
            history.map {
                it.toHistoryItem()
            }
        }.toCommonFlow()
    }
    
    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistory(
            fromLanguageCode = item.fromLanguageCode,
            toLanguageCode = item.toLanguageCode,
            fromText = item.fromText,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}