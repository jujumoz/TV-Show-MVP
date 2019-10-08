package com.sierra.presentation.favorite

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
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
import sierra.com.domain.usecase.GetFavoritesUseCase
import sierra.com.domain.usecase.RemoveFavoriteUseCase

@RunWith(MockitoJUnitRunner::class)
class FavoritesPresenterTest {

    @Mock
    private lateinit var view: FavoritesContract.FavoritesView
    @Mock
    private lateinit var getFavoriteUseCase: GetFavoritesUseCase
    @Mock
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase
    @Mock
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase
    @Mock
    private lateinit var show: TvShow
    @Mock
    private lateinit var lifeCycleOwner: LifecycleOwner

    private val presenter by lazy {
        FavoritesPresenter(
            view,
            getFavoriteUseCase,
            addFavoriteUseCase,
            removeFavoriteUseCase,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    private lateinit var lifeCycle: LifecycleRegistry

    @Before
    fun setUp() {
        Mockito.`when`(getFavoriteUseCase.build()).thenReturn(Single.just(listOf(show)))
        Mockito.`when`(addFavoriteUseCase.build(any())).thenReturn(Completable.complete())
        Mockito.`when`(removeFavoriteUseCase.build(any())).thenReturn(Completable.complete())

        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        lifeCycle.addObserver(presenter)

        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    @Test
    fun `should show favorite shows when start`() {
        lifeCycle.currentState = Lifecycle.State.STARTED

        verify(getFavoriteUseCase).build()
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