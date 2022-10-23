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

object ChartWeight {

    fun chartWeight(year:Int,month:Int,day:Int,dayOfWeek:Int,dialogManager: DialogManager,chartBinding: BarChart){

        val sunday = "${day-dayOfWeek+1}/${month+1}/$year"
        val monday = "${day-dayOfWeek+2}/${month+1}/$year"
        val tuesday = "${day-dayOfWeek+3}/${month+1}/$year"
        val wendnesday = "${day-dayOfWeek+4}/${month+1}/$year"
        val thursday = "${day-dayOfWeek+5}/${month+1}/$year"
        val friday = "${day-dayOfWeek+6}/${month+1}/$year"
        val saturday = "${day-dayOfWeek+7}/${month+1}/$year"


        val weightSunday =  ClearWord.allAmount(dialogManager,sunday,"Weight")*1f
        val weightMonday=  ClearWord.allAmount(dialogManager,monday,"Weight")*1f
        val weightTuesday =  ClearWord.allAmount(dialogManager,tuesday,"Weight")*1f
        val weightWendnesday=  ClearWord.allAmount(dialogManager,wendnesday,"Weight")*1f
        val weightThursday =  ClearWord.allAmount(dialogManager,thursday,"Weight")*1f
        val weightFriday =  ClearWord.allAmount(dialogManager,friday,"Weight")*1f
        val weightSaturday =  ClearWord.allAmount(dialogManager,saturday,"Weight")*1f

        ClearWord.settingChart(weightSunday,weightMonday,weightTuesday,
            weightWendnesday,weightThursday,weightFriday,weightSaturday,chartBinding,"Weight")

    }
}