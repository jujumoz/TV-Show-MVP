package com.sierra.presentation.di.module

import dagger.Module
import dagger.Provides
import sierra.com.data.db.entity.TvShowDB
import sierra.com.data.entity.*
import sierra.com.data.mapper.*
import sierra.com.domain.mapper.Mapper
import sierra.com.domain.model.*

@Module
class MapperModule {

    @Provides
    internal fun provideResultEntityMapper(impl: TvShowEntityMapper):
            Mapper<TvShowEntity, TvShow> = impl

    @Provides
    internal fun provideImageEntityMapper(impl: ImageEntityMapper):
            Mapper<ImageEntity, Image> = impl

    @Provides
    internal fun provideShowEntityMapper(impl: ShowEntityMapper):
            Mapper<ShowEntity, Show> = impl

    @Provides
    internal fun provideExternalsEntityMapper(impl: ExternalsEntityMapper):
            Mapper<ExternalsEntity, Externals> = impl

    @Provides
    internal fun provideNetworkEntityMapper(impl: NetworkEntityMapper):
            Mapper<NetworkEntity, Network> = impl

    @Provides
    internal fun provideRatingEntityMapper(impl: RatingEntityMapper):
            Mapper<RatingEntity, Rating> = impl

    @Provides
    internal fun provideScheduleEntityMapper(impl: ScheduleEntityMapper):
            Mapper<ScheduleEntity, Schedule> = impl

    @Provides
    internal fun provideCountryEntityMapper(impl: CountryEntityMapper):
            Mapper<CountryEntity, Country> = impl

    @Provides
    internal fun provideTvShowDBMapper(impl: TvShowDBMapper):
            Mapper<TvShow, TvShowDB> = impl
}