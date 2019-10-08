package com.sierra.presentation.di.module.activity

import android.app.Activity
import com.sierra.presentation.search.SearchActivity
import com.sierra.presentation.search.SearchContract
import com.sierra.presentation.search.SearchPresenter
import com.sierra.presentation.search.SearchRouter
import dagger.Binds
import dagger.Module

@Module
abstract class SearchModule {

    @Binds
    abstract fun provideSearchView(activity: SearchActivity): SearchContract.SearchView

    @Binds
    abstract fun provideSearchPresenter(presenter: SearchPresenter): SearchContract.SearchPresenter

    @Binds
    abstract fun provideActivity(activity: SearchActivity): Activity

    @Binds
    abstract fun provideSearchRouter(router: SearchRouter): SearchContract.SearchRouter
}