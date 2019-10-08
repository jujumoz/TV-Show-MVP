package com.sierra.presentation.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sierra.presentation.R
import sierra.com.domain.model.TvShow

class ShowAdapter(
    private val context: Context,
    private var data: List<TvShow>,
    private val scrollListener: ((position: Int) -> Unit)?,
    private val favoriteListener: (show: TvShow, selected: Boolean) -> Unit
) : RecyclerView.Adapter<ShowHolder>() {

    private var scrollLimitReached: Boolean = false

    val layoutManager: GridLayoutManager
        get() = GridLayoutManager(context, 2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_show, parent, false)
        return ShowHolder(view)
    }

    override fun onBindViewHolder(holder: ShowHolder, position: Int) {
        holder.setContent(context, data[position], favoriteListener)
        if (scrollLimitReached.not() && position == data.size - 1) {
            scrollListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(list: List<TvShow>) {
        if (data.size == list.size) {
            scrollLimitReached = true
        } else {
            data = list
            notifyDataSetChanged()
        }
    }
}