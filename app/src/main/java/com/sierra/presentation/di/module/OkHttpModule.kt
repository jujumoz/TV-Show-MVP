package com.sierra.presentation.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sierra.presentation.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
class OkHttpModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(logging)
            okHttpClient.addNetworkInterceptor(StethoInterceptor())
        }

        return okHttpClient.build()
    }
}