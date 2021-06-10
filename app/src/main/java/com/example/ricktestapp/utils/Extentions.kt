package com.example.ricktestapp.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.example.ricktestapp.R
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun View.setDefaultLayoutParams() {
    layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

/**
 * sets the given margin to start & end of the view
 * @param marginResId the side margin resource. set to 0 to remove margin
 */
fun View.setSideMargin(@DimenRes marginResId: Int) {
    val marginPixels = if (marginResId == 0) 0 else resources.getDimensionPixelSize(marginResId)
    layoutParams.let {
        if (it is ViewGroup.MarginLayoutParams) {
            it.marginEnd = marginPixels
            it.marginStart = marginPixels
            layoutParams = it
        }
    }
}

/**
 * sets the given margin to top & bottom of the view
 * @param marginResId the side margin resource. set to 0 to remove margin
 */
fun View.setVerticalMargin(@DimenRes marginResId: Int) {
    val marginPixels = if (marginResId == 0) 0 else resources.getDimensionPixelSize(marginResId)
    layoutParams.let {
        if (it is ViewGroup.MarginLayoutParams) {
            it.topMargin = marginPixels
            it.bottomMargin = marginPixels
            layoutParams = it
        }
    }
}

fun ImageView.loadUrl(url: String?, @DrawableRes placeholder: Int = R.drawable.ic_avatar_placeholder) {
    if (url.isNullOrEmpty()) return
    Picasso.get().load(url).placeholder(placeholder).into(this)
}

var OkHttpClient.Builder.isLoggingInterceptorEnabled: Boolean
    get() = interceptors().find { it is HttpLoggingInterceptor } != null
    set(value) {
        if (value) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }
    }