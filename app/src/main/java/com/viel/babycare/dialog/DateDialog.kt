package com.viel.babycare.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.github.mikephil.charting.charts.BarChart
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.databinding.FragmentAnalysisBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.fragments.AnalysisFragment
import com.viel.babycare.fragments.HomeFragment
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.*
import java.util.*

object DateDialog: DialogFragment(){

    val calendar:Calendar = Calendar.getInstance()
    private var date:String = "${getDay()}/${getMonth()+1}/${getYear()}"
    private var dateOfWeek:Int = 0
    var dateTv = ""
    private var day = getDay()
    private var month = getMonth()
    private var year = getYear()


    @RequiresApi(Build.VERSION_CODES.N)
    fun dateDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>, adapter: DialogActionAdapter,
                   dialogManager: DialogManager,tv:TextView?
    ):Dialog{
        val dayDialog = Dialog(mainActivity)
        dayDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dayDialog.setCanceledOnTouchOutside(false)
        dayDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dayDialog.setContentView(R.layout.dialog_date)
        tv?.setOnClickListener { dayDialog.show() }

        val picker = dayDialog.findViewById(R.id.dp_day) as DatePicker
        val btnOk = dayDialog.findViewById(R.id.btn_date_ok) as Button
        val btnCancel = dayDialog.findViewById(R.id.btn_date_cancel) as Button


        btnOk.setOnClickListener {
            day = picker.dayOfMonth
            month = picker.month
            year = picker.year
            date = "$day/${month+1}/$year"
            calendar.set(year,month,day)
            dateOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            dateTv = "${change(calendar.get(Calendar.DAY_OF_WEEK))}, $date"
            tv?.setText(dateTv)
            arr.clear()
            arr.addAll(dialogManager.getFinterDialog(date))
            adapter.notifyDataSetChanged()
            dayDialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dayDialog.dismiss()
        }
        return dayDialog
    }

    fun getDay1(): Int {
        val day = day
        return day
    }
    fun getMonth1():Int{
        val month = month
        return month
    }
    fun getYear1():Int{
        val year = year
        return year
    }

    fun getDate():String{
        val currentDate = date
        return currentDate
    }

    fun getDay():Int{
        val currentDay = calendar.get(Calendar.DATE)
        return currentDay
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

    fun change(day: Int):String {
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