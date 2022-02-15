package com.moataz.afternoonhadeeth.utils.helper

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.moataz.afternoonhadeeth.R

@BindingAdapter("imageUrl")
fun loadImage(view: View, imageUrl: String?) {
    val image: ImageView = view as ImageView
    image.load(imageUrl)
}

@BindingAdapter("imageUrlWithPlaceHolder")
fun loadImageWithPlaceHolder(view: View, imageUrl: String?) {
    val image: ImageView = view as ImageView
    image.load(imageUrl) {
        placeholder(R.drawable.folder_loading_image)
    }
}