package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.View
import android.widget.Button
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

object Actions {
    fun vibrateOnce(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vib = vibratorManager.defaultVibrator;
            vib.vibrate(VibrationEffect.createOneShot(20, 1))
        } else {
            val vib = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vib.vibrate(20)
        }
    }

    var counter = 0
    fun displayCounter(buttonCounter: Button) {
        buttonCounter.text = counter.toString()
    }

    fun counter(buttonCounter: Button) {
        counter++
        buttonCounter.text = counter.toString()
        if (buttonCounter.text == 999.toString()) {
            buttonCounter.text = 0.toString()
            counter = 0
        }
    }

    fun reset(buttonCounter: Button) {
        buttonCounter.text = 0.toString()
        counter = 0
    }
}