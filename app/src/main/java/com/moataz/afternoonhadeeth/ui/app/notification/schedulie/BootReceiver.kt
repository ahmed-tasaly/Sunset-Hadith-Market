package com.moataz.afternoonhadeeth.ui.app.notification.schedulie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            MorningNotification().setupMorningNotification(context)
            NightNotification().setupNightNotification(context)
            AfternoonNotification().setupAfternoonNotification(context)
        }
    }
}