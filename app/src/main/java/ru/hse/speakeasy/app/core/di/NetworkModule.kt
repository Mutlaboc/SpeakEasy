package ru.hse.speakeasy.app.core.di

import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import retrofit2.Retrofit
import ru.hse.speakeasy.app.core.TranslationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okhttpCallFactory: Lazy<Call.Factory>): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideTranslationApi(retrofit: Retrofit): TranslationApi {
        return retrofit.create(TranslationApi::class.java)
    }
}