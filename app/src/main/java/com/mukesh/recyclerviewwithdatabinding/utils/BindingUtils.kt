package com.mukesh.recyclerviewwithdatabinding.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(image: ImageView, url: String) {
    Glide.with(image)
        .load(url)
        .into(image)

}