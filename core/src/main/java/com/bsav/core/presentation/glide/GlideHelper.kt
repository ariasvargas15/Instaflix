package com.bsav.core.presentation.glide

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

private const val BASE_URL_IMAGES = "https://image.tmdb.org/t/p/original"

fun ImageView.loadImageFromUrl(url: String) {
    val requestOptions: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .centerCrop()
        .dontAnimate()
        .dontTransform()
        .priority(Priority.IMMEDIATE)
        .encodeFormat(Bitmap.CompressFormat.PNG)
        .format(DecodeFormat.DEFAULT)
    Glide.with(this)
        .applyDefaultRequestOptions(requestOptions)
        .load(url)
        .thumbnail(0.05f)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadImageFromPathWithBaseUrl(path: String) {
    this.loadImageFromUrl("$BASE_URL_IMAGES$path")
}
