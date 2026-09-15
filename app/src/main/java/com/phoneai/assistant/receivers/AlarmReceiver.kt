package com.phoneai.assistant.receivers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("AlarmReceiver", "Alarm triggered!")
        
        val alarmLabel = intent.getStringExtra("alarm_label") ?: "Alarm"
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, uri)
        ringtone.play()
        
        // Show notification
        Log.d("AlarmReceiver", "Playing alarm: $alarmLabel")
    }
}
