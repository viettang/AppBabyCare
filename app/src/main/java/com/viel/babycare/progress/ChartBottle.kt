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

object ChartBottle {

    fun chartBottle(year:Int,month:Int,day:Int,dayOfWeek:Int,dialogManager: DialogManager,chartBinding: BarChart){

        val sunday = "${day-dayOfWeek+1}/${month+1}/$year"
        val monday = "${day-dayOfWeek+2}/${month+1}/$year"
        val tuesday = "${day-dayOfWeek+3}/${month+1}/$year"
        val wendnesday = "${day-dayOfWeek+4}/${month+1}/$year"
        val thursday = "${day-dayOfWeek+5}/${month+1}/$year"
        val friday = "${day-dayOfWeek+6}/${month+1}/$year"
        val saturday = "${day-dayOfWeek+7}/${month+1}/$year"


        val bottleSunday =  ClearWord.allAmount(dialogManager,sunday,"Bottle")*1f
        val bottleMonday =  ClearWord.allAmount(dialogManager,monday,"Bottle")*1f
        val bottleTuesday =  ClearWord.allAmount(dialogManager,tuesday,"Bottle")*1f
        val bottleWendnesday=  ClearWord.allAmount(dialogManager,wendnesday,"Bottle")*1f
        val bottleThursday =  ClearWord.allAmount(dialogManager,thursday,"Bottle")*1f
        val bottleFriday =  ClearWord.allAmount(dialogManager,friday,"Bottle")*1f
        val bottleSaturday =  ClearWord.allAmount(dialogManager,saturday,"Bottle")*1f

        ClearWord.settingChart(bottleSunday,bottleMonday,bottleTuesday,bottleWendnesday
            ,bottleThursday,bottleFriday,bottleSaturday,chartBinding,"Milk")
    }
}