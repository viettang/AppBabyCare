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

object ChartHeight {

    fun chartHeight(year:Int,month:Int,day:Int,dayOfWeek:Int,dialogManager: DialogManager,chartBinding: BarChart){

        val sunday = "${day-dayOfWeek+1}/${month+1}/$year"
        val monday = "${day-dayOfWeek+2}/${month+1}/$year"
        val tuesday = "${day-dayOfWeek+3}/${month+1}/$year"
        val wendnesday = "${day-dayOfWeek+4}/${month+1}/$year"
        val thursday = "${day-dayOfWeek+5}/${month+1}/$year"
        val friday = "${day-dayOfWeek+6}/${month+1}/$year"
        val saturday = "${day-dayOfWeek+7}/${month+1}/$year"

        val heightSunday =  ClearWord.allAmount(dialogManager,sunday,"Height")*1f
        val heightMonday=  ClearWord.allAmount(dialogManager,monday,"Height")*1f
        val heightTuesday =  ClearWord.allAmount(dialogManager,tuesday,"Height")*1f
        val heightWendnesday=  ClearWord.allAmount(dialogManager,wendnesday,"Height")*1f
        val heightThursday =  ClearWord.allAmount(dialogManager,thursday,"Height")*1f
        val heightFriday =  ClearWord.allAmount(dialogManager,friday,"Height")*1f
        val heightSaturday =  ClearWord.allAmount(dialogManager,saturday,"Height")*1f

        ClearWord.settingChart(heightSunday,heightMonday,heightTuesday,heightWendnesday,
            heightThursday,heightFriday,heightSaturday,chartBinding,"Height")
    }
}