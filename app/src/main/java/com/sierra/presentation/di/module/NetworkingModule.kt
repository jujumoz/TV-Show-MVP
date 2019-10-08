package com.sierra.presentation.di.module

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

@Module(
    includes = [
        ServiceModule::class,
        RetrofitModule::class,
        OkHttpModule::class
    ]
)
class NetworkingModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()
}