package com.sierra.presentation.di.naming

import javax.inject.Named
import javax.inject.Qualifier

interface SchedulersNaming {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    @Named("SchedulersNamed_ui")
    annotation class UI

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    @Named("SchedulersNamed_io")
    annotation class IO
}