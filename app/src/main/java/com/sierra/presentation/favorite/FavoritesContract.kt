package com.sierra.presentation.favorite

import androidx.lifecycle.LifecycleObserver
import sierra.com.domain.model.TvShow

class FavoritesContract {

    interface FavoritesView {

        fun showFavorites(shows: List<TvShow>)

        fun showError()
    }

    interface FavoritesPresenter : LifecycleObserver {

        fun onFavoriteClick(show: TvShow, selected: Boolean)
    }
}