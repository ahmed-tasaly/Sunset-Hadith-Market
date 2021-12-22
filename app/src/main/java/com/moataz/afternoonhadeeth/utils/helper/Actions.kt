package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object Actions {
     fun vibrateOnce(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =  context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vib  = vibratorManager.defaultVibrator;
            vib.vibrate(VibrationEffect.createOneShot(20,1 ))
        } else {
            val vib  = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vib.vibrate(20)
        }
    }
}