package com.sierra.presentation.di.component

import android.app.Application
import com.sierra.presentation.TvShowApplication
import com.sierra.presentation.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkingModule::class,
        RepositoryModule::class,
        MapperModule::class,
        AndroidInjectionModule::class,
        SchedulersModule::class,
        ActivityModule::class,
        LocalDataProviderModule::class,
        RoomModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<TvShowApplication> {

    fun inject(application: Application)
}
