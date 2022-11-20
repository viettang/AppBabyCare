package com.viel.babycare.model

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.viel.babycare.R
import com.viel.babycare.ScreenStart
import com.viel.babycare.db.DialogManager
import com.viel.babycare.db.NotificationDatabase
import com.viel.babycare.dialog.DateDialog

class AlarmReceiver: BroadcastReceiver() {
    private lateinit var dialogManager:DialogManager

    @SuppressLint("ResourceAsColor")
    override fun onReceive(context: Context?, intent: Intent?) {

        val iT = intent?.getStringExtra("note")
        val i = Intent(context,ScreenStart::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_MUTABLE)

        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        dialogManager = DialogManager(context!!)

        val imgBitmap:Bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.alarm_notifi)
        val soundUri:Uri = Uri.parse("android.resource://${context.packageName}/${R.raw.mama}")
        val builder = NotificationCompat.Builder(context!!,"babycare")
            .setSmallIcon(R.drawable.ic_img_header)
            .setLargeIcon(imgBitmap)
            .setContentTitle("Babycare")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(soundUri)
            .setColor(R.color.orange)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        if (dialogManager.getAlarmDialog().size != 0) {
            val idAlarm = dialogManager.getAlarmDialogSort()[0].id
            val imgAlarm = dialogManager.getAlarmDialogSort()[0].img
            val noteAlarm = dialogManager.getAlarmDialogSort()[0].amount
            val timeAlarm = dialogManager.getAlarmDialogSort()[0].time
            val dateAlarm = dialogManager.getAlarmDialogSort()[0].date

            builder.setContentText(iT)
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(123, builder.build())

            NotificationDatabase.getInstance(context).notificationDao().
            insertNotification(Notification(imgNo = imgAlarm, noteNo = noteAlarm,
                timeNo = timeAlarm, dateNo = dateAlarm))

            dialogManager.deleteDialog(idAlarm)
        }
    }
}