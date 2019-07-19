package com.example.mvvmkoinsample.util

import android.widget.ImageView
import com.example.mvvmkoinsample.R
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Picasso.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(this)
    }
}