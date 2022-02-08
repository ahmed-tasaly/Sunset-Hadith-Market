package com.moataz.afternoonhadeeth.ui.jetpack.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.source.MorningHadiths
import com.moataz.afternoonhadeeth.ui.view.activity.MainActivity
import java.util.*

class MorningNotification : BroadcastReceiver() {
    private val CHANNEL_ID = "HADITH_MORNING_CHANNEL_ID"

    override fun onReceive(context: Context, intent: Intent?) {

        val notificationIntent = Intent(context, MainActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(notificationIntent)
        val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        Notification.Builder(context, CHANNEL_ID)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("حديث الصباح")
            .setContentText(MorningHadiths().morningHadith())
            .setSmallIcon(R.drawable.ic_notification)
            .setLights(Notification.FLAG_SHOW_LIGHTS, 1000, 500)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .build()
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Channel_HADITH_MORNING_CHANNEL_ID",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.enableLights(true)
        channel.lightColor = Color.WHITE
        channel.enableVibration(false)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(0, notification)
    }

    fun setupMorningNotification(context: Context) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        cal[Calendar.HOUR_OF_DAY] = 7
        cal[Calendar.MINUTE] = 30
        cal[Calendar.SECOND] = 0
        if (cal.timeInMillis > System.currentTimeMillis()) {
            val notificationIntent = Intent(context, MorningNotification::class.java)
            @SuppressLint("UnspecifiedImmutableFlag") val broadcast = PendingIntent.getBroadcast(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
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