package ru.kheynov.kmm_translator.translate.data.translate


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*
import ru.kheynov.kmm_translator.core.domain.language.Language
import ru.kheynov.kmm_translator.translate.domain.translate.TranslateClient
import ru.kheynov.kmm_translator.translate.domain.translate.TranslateError
import ru.kheynov.kmm_translator.translate.domain.translate.TranslateException

class KtorTranslateClient(
    private val httpClient: HttpClient,
) : TranslateClient {
    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): String {
        val result = try {
            httpClient.post {
                url("https://translate.s.kheynov.ru/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDTO(
                        textToTranslate = fromText,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        } catch (e: IOException) {
            println(e.stackTraceToString())
            throw TranslateException(TranslateError.SERVICE_UNAVAILABLE)
        } catch (e: Exception) {
            throw e
        }
        when (result.status.value) {
            in 200..299 -> Unit
            500         -> throw TranslateException(TranslateError.SERVER_ERROR)
            in 400..499 -> throw TranslateException(TranslateError.CLIENT_ERROR)
            else        -> throw TranslateException(TranslateError.UNKNOWN_ERROR)
        }
        return try {
            result.body<TranslatedDTO>().translatedText
        } catch (e: Exception) {
            throw TranslateException(TranslateError.SERVER_ERROR)
        }
    }
}