package com.moataz.afternoonhadeeth.ui.view.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.source.NightHadiths
import com.moataz.afternoonhadeeth.ui.view.activity.DisplayNotificationHadith
import java.util.*

class NightNotification : BroadcastReceiver() {

    private val CHANNEL_ID = "HADITH_NIGHT_CHANNEL_ID"
    private var NOTIFICATION_TITLE = "حديث المساء"
    private var NOTIFICATION_MESSAGE = NightHadiths().nightHadith()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent?) {

        // send content of notification to activity
        val notificationIntent = Intent(context, DisplayNotificationHadith::class.java)
        notificationIntent.putExtra("hadithNotification", NOTIFICATION_MESSAGE)
        notificationIntent.putExtra("titleNotification", NOTIFICATION_TITLE)

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(notificationIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        Notification.Builder(context, CHANNEL_ID)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_notification)
            .setLights(Notification.FLAG_SHOW_LIGHTS, 1000, 500)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .setColor(Color.BLUE)
            .setLights(Color.BLUE, 500, 500)
            .build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Channel_HADITH_NIGHT_CHANNEL_ID",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.enableLights(true)
        channel.lightColor = Color.WHITE
        channel.enableVibration(false)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(0, notification)
    }

    fun setupNightNotification(context: Context) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        cal[Calendar.HOUR_OF_DAY] = 22
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        if (cal.timeInMillis > System.currentTimeMillis()) {
            val notificationIntent = Intent(context, NightNotification::class.java)
            @SuppressLint("UnspecifiedImmutableFlag") val broadcast = PendingIntent.getBroadcast(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                (24 * 60 * 60 * 1000).toLong(),
                broadcast
            ) //Repeat every 24 h
        }
    }
}