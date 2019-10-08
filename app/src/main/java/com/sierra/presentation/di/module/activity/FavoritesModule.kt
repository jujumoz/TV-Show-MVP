package com.sierra.presentation.di.module.activity

import android.app.Activity
import com.sierra.presentation.favorite.FavoritesActivity
import com.sierra.presentation.favorite.FavoritesContract
import com.sierra.presentation.favorite.FavoritesPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class FavoritesModule {

    @Binds
    abstract fun provideFavoritesView(activity: FavoritesActivity): FavoritesContract.FavoritesView

    @Binds
    abstract fun provideFavoritesPresenter(presenter: FavoritesPresenter): FavoritesContract.FavoritesPresenter

    @Binds
    abstract fun provideActivity(activity: FavoritesActivity): Activity
}