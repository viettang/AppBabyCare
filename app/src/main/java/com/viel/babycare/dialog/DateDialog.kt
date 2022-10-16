package com.viel.babycare.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.Resources
import android.widget.DatePicker
import android.widget.TextView
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import java.lang.StringBuilder
import java.util.*

object DateDialog {

    fun dateDialog(mainActivity: MainActivity,tvDate:TextView){
        val calendar:Calendar = Calendar.getInstance()
        var currentDate = calendar.get(Calendar.DATE)
        var currentMonth = calendar.get(Calendar.MONTH)
        var currentYear = calendar.get(Calendar.YEAR)
        var day = calendar.get(Calendar.DAY_OF_WEEK)
        change(day)
        val dayDialog = DatePickerDialog(mainActivity,null, currentYear, currentMonth,
            currentDate)
        tvDate.text = StringBuilder().append(change(day)).append(',').append(currentDate).append("-")
            .append(currentMonth+1).append("-").append(currentYear)
        tvDate.setOnClickListener {
            dayDialog.show()
        }
    }

    private fun change(day:Int):String {
        when(day){
            2 -> return "Monday"
            3 -> return "Tuesday"
            4 -> return "Wendnesday"
            5 -> return "Thursday"
            6 -> return "Friday"
            7 -> return "Saturday"
            else -> return "Sunday"
        }
    }

}