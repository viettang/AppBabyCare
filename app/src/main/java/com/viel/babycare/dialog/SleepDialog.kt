package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.progress.GetTime

class SleepDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    @RequiresApi(Build.VERSION_CODES.N)
    fun sleepDialog(mainActivity: MainActivity){
        val dialog = Dialog(mainActivity)
        dialog.setContentView(R.layout.dialog_sleep)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentSleep:TextView = dialog.findViewById(R.id.tv_fall_sleep)
        val timeCurrentWakeup:TextView = dialog.findViewById(R.id.tv_wake_up)
        timeCurrentSleep.text = time.cTime()
        timeCurrentWakeup.text = time.cTime()
        dialog.show()

        timeCurrentSleep.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrentSleep)
        }
        timeCurrentWakeup.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrentWakeup)
        }
    }
}