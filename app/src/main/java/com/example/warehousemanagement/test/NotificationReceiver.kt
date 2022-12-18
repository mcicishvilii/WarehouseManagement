package com.example.warehousemanagement.test

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class NotificationReceiver :BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        val service = NotificationService(context!!)
        service.scheduleNotification()
//        val service = NotificationService(context!!)
//        service.alarmManager
//        service.notificationManager

//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        val notification = NotificationCompat.Builder(context!!, channelID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle(intent!!.getStringExtra(titleExtra))
//            .setContentText(intent!!.getStringExtra(messageExtra))
//            .build()
//
//        notificationManager.notify(notificationID, notification)
        Log.d("mcicishvili","called onreceive")
    }
}