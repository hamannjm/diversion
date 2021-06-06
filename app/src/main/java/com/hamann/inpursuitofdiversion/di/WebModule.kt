package com.hamann.inpursuitofdiversion.di

import android.util.Log
import com.google.gson.Gson
import com.hamann.inpursuitofdiversion.web.SearchService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class WebModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.giantbomb.com/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor {
                            Log.d("WEB_REQ", it)
                        }.also { it.level = HttpLoggingInterceptor.Level.BASIC }
                    )
                    .callTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Singleton
    @Provides
    fun providesSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}