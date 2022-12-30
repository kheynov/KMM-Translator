package ru.kheynov.kmm_translator.android.di

import android.app.Application
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import ru.kheynov.kmm_translator.database.TranslateDatabase
import ru.kheynov.kmm_translator.translate.data.history.SqlDelightHistoryDataSource
import ru.kheynov.kmm_translator.translate.data.local.DatabaseDriverFactory
import ru.kheynov.kmm_translator.translate.data.remote.HttpClientFactory
import ru.kheynov.kmm_translator.translate.data.translate.KtorTranslateClient
import ru.kheynov.kmm_translator.translate.domain.history.HistoryDataSource
import ru.kheynov.kmm_translator.translate.domain.translate.TranslateClient
import ru.kheynov.kmm_translator.translate.domain.translate.TranslateUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }
    
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }
    
    @Provides
    @Singleton
    fun provideDatabaseDriverFactory(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }
    
    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }
    
    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource,
    ): TranslateUseCase {
        return TranslateUseCase(
            client,
            dataSource,
        )
    }
}