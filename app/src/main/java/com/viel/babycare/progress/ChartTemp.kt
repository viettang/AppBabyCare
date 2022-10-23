package com.viel.babycare.progress

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.viel.babycare.databinding.FragmentAnalysisBinding
import com.viel.babycare.db.DialogManager

object ChartTemp {

    fun chartTemp(year:Int,month:Int,day:Int,dayOfWeek:Int,dialogManager: DialogManager,chartBinding: BarChart){

        val sunday = "${day-dayOfWeek+1}/${month+1}/$year"
        val monday = "${day-dayOfWeek+2}/${month+1}/$year"
        val tuesday = "${day-dayOfWeek+3}/${month+1}/$year"
        val wendnesday = "${day-dayOfWeek+4}/${month+1}/$year"
        val thursday = "${day-dayOfWeek+5}/${month+1}/$year"
        val friday = "${day-dayOfWeek+6}/${month+1}/$year"
        val saturday = "${day-dayOfWeek+7}/${month+1}/$year"

        val tempSunday =  ClearWord.allAmount(dialogManager,sunday,"Temp")*1f
        val tempMonday=  ClearWord.allAmount(dialogManager,monday,"Temp")*1f
        val tempTuesday =  ClearWord.allAmount(dialogManager,tuesday,"Temp")*1f
        val tempWendnesday=  ClearWord.allAmount(dialogManager,wendnesday,"Temp")*1f
        val tempThursday =  ClearWord.allAmount(dialogManager,thursday,"Temp")*1f
        val tempFriday =  ClearWord.allAmount(dialogManager,friday,"Temp")*1f
        val tempSaturday =  ClearWord.allAmount(dialogManager,saturday,"Temp")*1f

        ClearWord.settingChart(tempSunday,tempMonday,tempTuesday,tempWendnesday,
            tempThursday,tempFriday,tempSaturday,chartBinding,"Temp")
    }
}