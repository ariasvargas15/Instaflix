package com.bsav.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

private const val BASE_URL_IMAGES = "https://image.tmdb.org/t/p/original"

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImageFromPathWithBaseUrl(path: String) {
    this.loadImageFromUrl("$BASE_URL_IMAGES$path")
}
