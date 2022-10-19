package com.viel.babycare.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import java.lang.StringBuilder
import java.util.*

object DateDialog: DialogFragment(){

    val calendar:Calendar = Calendar.getInstance()
    fun dateDialog(mainActivity: MainActivity,tvDate:TextView){
        val dayDialog = DatePickerDialog(mainActivity,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,null, getYear(), getMonth(),
            getDate())

        tvDate.text = StringBuilder().append(change(getDayOfWeek())).append(',').append(getDate()).append("-")
            .append(getMonth()+1).append("-").append(getYear())
        tvDate.setOnClickListener {
            dayDialog.show()
        }
    }

    fun getDate():Int{
        val currentDate = calendar.get(Calendar.DATE)
        return currentDate
    }
    fun getMonth():Int{
        val currentMonth = calendar.get(Calendar.MONTH)
        return currentMonth
    }
    fun getYear():Int{
        val currentYear = calendar.get(Calendar.YEAR)
        return currentYear
    }
    fun getDayOfWeek():Int{
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        return day
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