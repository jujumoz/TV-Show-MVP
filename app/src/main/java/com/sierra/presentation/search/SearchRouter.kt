package com.sierra.presentation.search

import android.app.Activity
import android.content.Intent
import com.sierra.presentation.favorite.FavoritesActivity
import javax.inject.Inject

class SearchRouter @Inject constructor(
    private val activity: Activity?
) : SearchContract.SearchRouter {

    override fun goToFavorite() {
        activity?.let {
            Intent(activity, FavoritesActivity::class.java).also {
                activity.startActivity(it)
            }
        }
    }
}