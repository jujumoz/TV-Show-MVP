package com.sierra.presentation.favorite

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.sierra.presentation.R
import com.sierra.presentation.common.ShowAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.view_tv_show_list.*
import sierra.com.domain.model.TvShow
import javax.inject.Inject

class FavoritesActivity : AppCompatActivity(), FavoritesContract.FavoritesView {

    @Inject
    lateinit var presenter: FavoritesContract.FavoritesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        lifecycle.addObserver(presenter)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        text_view_tv_show_list.setText(R.string.favorites_empty_place_holder)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showFavorites(shows: List<TvShow>) {
        if (shows.isNotEmpty()) {
            val adapter =
                ShowAdapter(
                    this,
                    shows,
                    null
                ) { show, selected -> presenter.onFavoriteClick(show, selected) }
            recycler_view_tv_show_list.layoutManager = adapter.layoutManager
            recycler_view_tv_show_list.adapter = adapter

            text_view_tv_show_list.isInvisible = true
            recycler_view_tv_show_list.isVisible = true
        } else {
            text_view_tv_show_list.isVisible = true
            recycler_view_tv_show_list.isInvisible = true
        }
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_SHORT).show()
    }
}