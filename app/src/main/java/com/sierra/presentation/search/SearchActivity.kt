package com.sierra.presentation.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.sierra.presentation.R
import com.sierra.presentation.common.ShowAdapter
import com.sierra.presentation.hideSoftKeyboard
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.view_tv_show_list.*
import sierra.com.domain.model.TvShow
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchContract.SearchView {

    @Inject
    lateinit var presenter: SearchContract.SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        lifecycle.addObserver(presenter)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        configureSearchInputListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_favorite -> {
                presenter.onGoToFavoriteClick()
            }
        }
        return true
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_SHORT).show()
    }

    private fun configureSearchInputListeners() {
        edit_text_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (edit_text_search.text.toString().isNotEmpty()) {
                    presenter.onSearchClick(edit_text_search.text.toString())
                }
            }
            false
        }
        button_search.setOnClickListener {
            edit_text_search.text?.toString()?.let {
                if (edit_text_search.text.toString().isNotEmpty()) {
                    presenter.onSearchClick(edit_text_search.text.toString())
                }
                hideSoftKeyboard()
            }
        }
    }

    override fun showTvShows(list: List<TvShow>) {
        if (list.isNotEmpty()) {
            val adapter = ShowAdapter(this, list, { presenter.getMoreResult() })
            { show, selected -> presenter.onFavoriteClick(show, selected) }
            recycler_view_tv_show_list.layoutManager = adapter.layoutManager
            recycler_view_tv_show_list.adapter = adapter

            text_view_tv_show_list.isInvisible = true
            recycler_view_tv_show_list.isVisible = true
        } else {
            text_view_tv_show_list.isVisible = true
            recycler_view_tv_show_list.isInvisible = true
        }
    }

    override fun showMoreTvShows(list: List<TvShow>) {
        (recycler_view_tv_show_list.adapter as? ShowAdapter)?.updateData(list)
    }
}