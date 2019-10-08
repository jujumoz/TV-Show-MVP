package com.sierra.presentation.di.module

import com.sierra.presentation.di.naming.SchedulersNaming
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulersModule {

    @Provides
    @SchedulersNaming.UI
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @SchedulersNaming.IO
    fun backgroundScheduler(): Scheduler = Schedulers.io()
}