package com.sierra.presentation.common

import android.content.Context
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sierra.presentation.displayImage
import kotlinx.android.synthetic.main.item_list_show.view.*
import sierra.com.domain.model.TvShow

class ShowHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

    fun setContent(
        context: Context,
        tvShow: TvShow,
        favoriteListener: (show: TvShow, selected: Boolean) -> Unit
    ) {
        itemView.image_view_show_list_item.displayImage(context, tvShow.image)
        itemView.image_view_show_list_favorite.isVisible = tvShow.star
        itemView.toggle_button_show_list.isChecked = tvShow.favorite

        itemView.toggle_button_show_list.setOnCheckedChangeListener { _, favorite ->
            favoriteListener.invoke(tvShow, favorite)
        }
    }
}