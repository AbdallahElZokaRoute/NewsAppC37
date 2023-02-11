package com.route.newsappc37

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadImage")
fun ImageView.loadImageFromURL(url: String) {
    Glide.with(this).load(url).into(this)
}