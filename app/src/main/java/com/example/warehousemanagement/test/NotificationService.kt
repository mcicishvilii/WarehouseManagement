package com.example.warehousemanagement.test

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.screens.brands.DashboardFragment
import java.util.*

const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class NotificationService (private val context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @RequiresApi(Build.VERSION_CODES.M)
    fun scheduleNotification()
    {
        val intent = Intent(context, BlankFragment::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationID,
            intent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            1702005018000,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}


//val notification = NotificationCompat.Builder(context!!, channelID)
//    .setSmallIcon(R.drawable.ic_launcher_foreground)
//    .setContentTitle(intent!!.getStringExtra(titleExtra))
//    .setContentText(intent!!.getStringExtra(messageExtra))
//    .build()
//
//
//
//notificationManager.notify(notificationID, notification)