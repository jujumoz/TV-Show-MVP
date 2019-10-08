package com.sierra.presentation.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import sierra.com.data.db.AppDatabase
import sierra.com.data.db.RoomConstants
import sierra.com.data.db.dao.TvShowDao
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun providesRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, RoomConstants.DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    internal fun providesTvShowDao(appDatabase: AppDatabase): TvShowDao {
        return appDatabase.tvShowDao()
    }
}