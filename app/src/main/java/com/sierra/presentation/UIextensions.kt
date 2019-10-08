package com.sierra.presentation

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sierra.presentation.di.module.GlideApp
import java.util.*

fun Activity.hideSoftKeyboard() {
    val focusedView = currentFocus
    focusedView?.let { view ->
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun ImageView.displayImage(context: Context?, url: String?) {
    context ?: run { return }
    url ?: run { return }
    GlideApp.with(context)
        .load(url)
        .thumbnail(0.1f)
        .error(getColor())
        .placeholder(android.R.color.darker_gray)
        .transition(DrawableTransitionOptions.withCrossFade())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

private fun getColor(): Int {
    val list = listOf(
        android.R.color.holo_blue_dark,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_red_dark,
        android.R.color.holo_purple
    )
    return list[Random().nextInt(list.size)]
}