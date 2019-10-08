package com.sierra.presentation.favorite

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.sierra.presentation.di.naming.SchedulersNaming
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import sierra.com.domain.model.TvShow
import sierra.com.domain.usecase.AddFavoriteUseCase
import sierra.com.domain.usecase.GetFavoritesUseCase
import sierra.com.domain.usecase.RemoveFavoriteUseCase
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
    private val view: FavoritesContract.FavoritesView,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    @SchedulersNaming.UI
    private val uiScheduler: Scheduler,
    @SchedulersNaming.IO
    private val ioScheduler: Scheduler
) : FavoritesContract.FavoritesPresenter {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        getFavoritesUseCase.build()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = { view.showFavorites(it) },
                onError = { view.showError() }
            )
    }

    override fun onFavoriteClick(show: TvShow, selected: Boolean) {
        val completable = if (selected) {
            addFavoriteUseCase.build(AddFavoriteUseCase.Params(show))
        } else {
            removeFavoriteUseCase.build(RemoveFavoriteUseCase.Params(show))
        }
        completable
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onComplete = { },
                onError = { view.showError() }
            )
    }
}