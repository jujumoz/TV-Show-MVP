package com.sierra.presentation.di.module

import com.sierra.presentation.di.module.activity.FavoritesModule
import com.sierra.presentation.di.module.activity.SearchModule
import com.sierra.presentation.di.naming.ActivityScope
import com.sierra.presentation.favorite.FavoritesActivity
import com.sierra.presentation.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FavoritesModule::class])
    internal abstract fun favoritesActivity(): FavoritesActivity
}