package com.sierra.presentation.di.module

import dagger.Module
import dagger.Provides
import sierra.com.data.repository.FavoriteRepositoryImpl
import sierra.com.data.repository.mock.TvShowRepositoryMock
import sierra.com.domain.repository.FavoriteRepository
import sierra.com.domain.repository.TvShowRepository

@Module
class RepositoryModule {

    @Provides
    internal fun provideFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository = impl

    @Provides
    internal fun provideTvShowRepository(impl: TvShowRepositoryMock): TvShowRepository = impl
}