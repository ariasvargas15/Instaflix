package com.bsav.core.presentation.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

private const val BASE_URL_IMAGES = "https://image.tmdb.org/t/p/original"

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .thumbnail(0.05f)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadImageFromPathWithBaseUrl(path: String) {
    this.loadImageFromUrl("$BASE_URL_IMAGES$path")
}
