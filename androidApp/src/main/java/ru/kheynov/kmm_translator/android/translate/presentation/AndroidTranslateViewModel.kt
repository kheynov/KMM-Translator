package ru.kheynov.kmm_translator.android.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kheynov.kmm_translator.translate.domain.history.HistoryDataSource
import ru.kheynov.kmm_translator.translate.domain.translate.TranslateUseCase
import ru.kheynov.kmm_translator.translate.presentation.TranslateEvent
import ru.kheynov.kmm_translator.translate.presentation.TranslateViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val translateUseCase: TranslateUseCase,
    private val historyDataSource: HistoryDataSource,
) : ViewModel() {
    private val viewModel by lazy {
        TranslateViewModel(
            translateUseCase = translateUseCase,
            historyDataSource = historyDataSource,
            coroutineScope = viewModelScope
        )
    }
    
    val state = viewModel.state
    fun onEvent(event: TranslateEvent) = viewModel.onEvent(event)
}