package com.sierra.presentation.search

import androidx.lifecycle.LifecycleObserver
import sierra.com.domain.model.TvShow

interface SearchContract {

    interface SearchView {

        fun showTvShows(list: List<TvShow>)

        fun showMoreTvShows(list: List<TvShow>)

        fun showError()
    }

    interface SearchPresenter : LifecycleObserver {

        fun onSearchClick(input: String)

        fun getMoreResult()

        fun onFavoriteClick(show: TvShow, selected: Boolean)

        fun onGoToFavoriteClick()
    }

    interface SearchRouter {

        fun goToFavorite()
    }
}