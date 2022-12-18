package com.example.warehousemanagement.test

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.databinding.FragmentBlankBinding
import java.util.*

class BlankFragment  : BaseFragment<FragmentBlankBinding>(FragmentBlankBinding::inflate) {

    private val noti: NotificationService by lazy { NotificationService(requireContext()) }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun viewCreated() {




        binding.submitButton.setOnClickListener {
//            Log.d("mcicish", getTime().toString())
//            Log.d("mcicish", System.currentTimeMillis().toString())
//            createAlarm(requireContext(),1702005018000)
            noti.scheduleNotification()
        }
    }

    override fun listeners() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun createAlarm(context: Context,time:Long){
        val alarmManager =context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        Log.d("receivedtime","time $time")
        val pendingIntent = PendingIntent.getBroadcast(context, Date().month+1, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager?.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun getTime(): Long {
//        val minute = binding.timePicker.minute
//        val hour = binding.timePicker.hour
//        val day = binding.datePicker.dayOfMonth
//        val month = binding.datePicker.month
//        val year = binding.datePicker.year
//
//        val calendar = Calendar.getInstance()
//        calendar.set(year, month, day, hour, minute)
//        return calendar.timeInMillis
//    }

}