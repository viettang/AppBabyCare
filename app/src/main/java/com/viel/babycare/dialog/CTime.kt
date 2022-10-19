package com.viel.babycare.dialog

import android.app.Dialog
import android.icu.util.Calendar
import android.os.Build
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.viel.babycare.R
import java.lang.StringBuilder

class CTime {

    @RequiresApi(Build.VERSION_CODES.N)
    fun cTime(): StringBuilder {
        val calendar: Calendar = Calendar.getInstance()
        val hour:Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute:Int = calendar.get(Calendar.MINUTE)
        val string: StringBuilder = StringBuilder().append(hour).append(":").append(minute)
        return string
    }
}