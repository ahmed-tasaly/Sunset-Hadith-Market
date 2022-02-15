package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Button

class CounterActions {

    fun displayCounter(buttonCounter: Button) {
        val counter = PrefConfig.loadTotalFromPref(buttonCounter.context)
        buttonCounter.text = counter.toString()
    }

    fun addCounter(buttonCounter: Button) {
        var counter = PrefConfig.loadTotalFromPref(buttonCounter.context)
        counter++
        PrefConfig.saveTotalInPref(buttonCounter.context, counter)
        buttonCounter.text = counter.toString()
        if (buttonCounter.text == 999.toString()) {
            resetCounter(buttonCounter)
        }
    }

    fun resetCounter(buttonCounter: Button) {
        val counter = 0
        PrefConfig.saveTotalInPref(buttonCounter.context, counter)
        buttonCounter.text = counter.toString()
    }

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
}