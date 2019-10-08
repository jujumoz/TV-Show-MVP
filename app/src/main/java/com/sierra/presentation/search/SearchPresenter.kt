package com.sierra.presentation.search

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.sierra.presentation.di.naming.SchedulersNaming
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import sierra.com.domain.model.TvShow
import sierra.com.domain.usecase.AddFavoriteUseCase
import sierra.com.domain.usecase.GetTvShowsUseCase
import sierra.com.domain.usecase.RemoveFavoriteUseCase
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val view: SearchContract.SearchView,
    private val router: SearchContract.SearchRouter,
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    @SchedulersNaming.UI
    private val uiScheduler: Scheduler,
    @SchedulersNaming.IO
    private val ioScheduler: Scheduler
) : SearchContract.SearchPresenter {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        getTvShowsUseCase.build()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = { view.showTvShows(it) },
                onError = { it.printStackTrace() }
            )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        getTvShowsUseCase.refreshFavorite()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = { view.showTvShows(it) },
                onError = { it.printStackTrace() }
            )
    }

    override fun onSearchClick(input: String) {
        getResults(input)
    }

    override fun getMoreResult() {
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

    override fun onGoToFavoriteClick() {
        router.goToFavorite()
    }

    private fun getResults(input: String) {
        getTvShowsUseCase.build(GetTvShowsUseCase.Params(input))
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = { view.showTvShows(it) },
                onError = { view.showError() }
            )
    }
}