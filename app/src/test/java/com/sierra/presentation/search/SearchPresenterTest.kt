package com.sierra.presentation.search

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import sierra.com.domain.model.TvShow
import sierra.com.domain.usecase.AddFavoriteUseCase
import sierra.com.domain.usecase.GetTvShowsUseCase
import sierra.com.domain.usecase.RemoveFavoriteUseCase

private const val SEARCH = "war"

@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTest {

    @Mock
    private lateinit var view: SearchContract.SearchView
    @Mock
    private lateinit var router: SearchContract.SearchRouter
    @Mock
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase
    @Mock
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase
    @Mock
    private lateinit var getTvShowsUseCase: GetTvShowsUseCase
    @Mock
    private lateinit var show: TvShow
    @Mock
    private lateinit var lifeCycleOwner: LifecycleOwner

    private val presenter by lazy {
        SearchPresenter(
            view,
            router,
            getTvShowsUseCase,
            addFavoriteUseCase,
            removeFavoriteUseCase,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    private lateinit var lifeCycle: LifecycleRegistry

    @Before
    fun setUp() {
        Mockito.`when`(getTvShowsUseCase.build(anyOrNull())).thenReturn(Single.just(listOf(show)))
        Mockito.`when`(getTvShowsUseCase.refreshFavorite()).thenReturn(Single.just(listOf(show)))
        Mockito.`when`(addFavoriteUseCase.build(anyOrNull())).thenReturn(Completable.complete())
        Mockito.`when`(removeFavoriteUseCase.build(anyOrNull())).thenReturn(Completable.complete())

        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        lifeCycle.addObserver(presenter)

        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    @Test
    fun `should show default shows when start`() {
        lifeCycle.currentState = Lifecycle.State.STARTED

        verify(getTvShowsUseCase).build()
        verify(view, atLeastOnce()).showTvShows(listOf(show))
        verify(view, never()).showError()
    }

    @Test
    fun `should show shows when search click`() {
        lifeCycle.currentState = Lifecycle.State.RESUMED

        presenter.onSearchClick(SEARCH)

        verify(getTvShowsUseCase).build(GetTvShowsUseCase.Params(SEARCH))
        verify(view, atLeastOnce()).showTvShows(listOf(show))
        verify(view, never()).showError()
    }

    @Test
    fun `should show error when get shows failed`() {
        Mockito.`when`(getTvShowsUseCase.build(GetTvShowsUseCase.Params(SEARCH)))
            .thenReturn(Single.error(Exception()))

        lifeCycle.currentState = Lifecycle.State.RESUMED

        presenter.onSearchClick(SEARCH)

        verify(getTvShowsUseCase).build(GetTvShowsUseCase.Params(SEARCH))
        verify(view, atLeastOnce()).showTvShows(listOf(show))
        verify(view).showError()
    }

    @Test
    fun `should remove favorite when click on remove favorite`() {
        lifeCycle.currentState = Lifecycle.State.RESUMED

        presenter.onFavoriteClick(show, true)

        verify(addFavoriteUseCase).build(AddFavoriteUseCase.Params(show))
        verify(removeFavoriteUseCase, never()).build(any())
        verify(view, never()).showError()
    }

    @Test
    fun `should add favorite when click on add favorite`() {
        lifeCycle.currentState = Lifecycle.State.RESUMED

        presenter.onFavoriteClick(show, false)

        verify(addFavoriteUseCase, never()).build(any())
        verify(removeFavoriteUseCase).build(RemoveFavoriteUseCase.Params(show))
        verify(view, never()).showError()
    }
}