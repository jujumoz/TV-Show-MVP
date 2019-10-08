package com.sierra.presentation.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import sierra.com.data.networking.ShowService

@Module
class ServiceModule {

    @Provides
    internal fun provideShowService(retrofit: Retrofit): ShowService =
        retrofit.create(ShowService::class.java)
}