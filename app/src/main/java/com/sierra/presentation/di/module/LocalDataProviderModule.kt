package com.sierra.presentation.di.module

import dagger.Module
import dagger.Provides
import sierra.com.data.db.provider.LocalDataProvider
import sierra.com.data.db.provider.TvShowDataProvider
import sierra.com.domain.model.TvShow
import javax.inject.Singleton

@Module
class LocalDataProviderModule {

    @Singleton
    @Provides
    fun providesTvShowProvider(impl: TvShowDataProvider): LocalDataProvider<TvShow> = impl
}
