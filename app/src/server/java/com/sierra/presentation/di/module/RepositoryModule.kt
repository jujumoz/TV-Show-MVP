package com.sierra.presentation.di.module

import dagger.Module
import dagger.Provides
import sierra.com.data.repository.FavoriteRepositoryImpl
import sierra.com.data.repository.TvShowRepositoryImpl
import sierra.com.repository.FavoriteRepository
import sierra.com.repository.TvShowRepository

@Module
class RepositoryModule {

    @Provides
    internal fun provideFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository = impl

    @Provides
    internal fun provideTvShowRepository(impl: TvShowRepositoryImpl): TvShowRepository = impl
}