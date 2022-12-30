package ru.kheynov.kmm_translator.android.translate.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.kheynov.kmm_translator.core.presentation.UiLanguage

@Composable
fun SmallLanguageIcon(
    language: UiLanguage,
    modifier: Modifier = Modifier,
) {
    AsyncImage(model = language.drawableRes, contentDescription = language.language.langName,
        modifier = modifier.size(25.dp))
}