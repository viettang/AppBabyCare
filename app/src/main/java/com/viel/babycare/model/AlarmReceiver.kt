package com.viel.babycare.model

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.viel.babycare.Music
import com.viel.babycare.R
import com.viel.babycare.db.DialogManager
import com.viel.babycare.fragments.HomeFragment

class AlarmReceiver(
    private val callback:EnableAlarm
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        callback.enableAlarm()
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val soundUri:Uri = Uri.parse("android.resource://raw/${R.raw.mama}")
        val builder = NotificationCompat.Builder(context!!,"babycare")
            .setSmallIcon(R.drawable.ic_img_header)
            .setContentTitle("Babycare")
            .setContentText("ahvchhqguuquduhq")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(soundUri)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())
    }
}
interface EnableAlarm{
    fun enableAlarm()
}