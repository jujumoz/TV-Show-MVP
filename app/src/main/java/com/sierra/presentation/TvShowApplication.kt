package com.sierra.presentation

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.sierra.presentation.di.component.ApplicationComponent
import com.sierra.presentation.di.component.DaggerApplicationComponent
import com.sierra.presentation.di.module.ApplicationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import javax.inject.Inject

class TvShowApplication : Application(), HasActivityInjector {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initGraph()
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    protected fun initGraph() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}