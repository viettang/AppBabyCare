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

object ChartSolid {

    fun chartSolid(year:Int,month:Int,day:Int,dayOfWeek:Int,dialogManager: DialogManager,chartBinding: BarChart){

        val sunday = "${day-dayOfWeek+1}/${month+1}/$year"
        val monday = "${day-dayOfWeek+2}/${month+1}/$year"
        val tuesday = "${day-dayOfWeek+3}/${month+1}/$year"
        val wendnesday = "${day-dayOfWeek+4}/${month+1}/$year"
        val thursday = "${day-dayOfWeek+5}/${month+1}/$year"
        val friday = "${day-dayOfWeek+6}/${month+1}/$year"
        val saturday = "${day-dayOfWeek+7}/${month+1}/$year"


        val solidsSunday =  ClearWord.allAmount(dialogManager,sunday,"Solids")*1f
        val solidsMonday=  ClearWord.allAmount(dialogManager,monday,"Solids")*1f
        val solidsTuesday =  ClearWord.allAmount(dialogManager,tuesday,"Solids")*1f
        val solidsWendnesday=  ClearWord.allAmount(dialogManager,wendnesday,"Solids")*1f
        val solidsThursday =  ClearWord.allAmount(dialogManager,thursday,"Solids")*1f
        val solidsFriday =  ClearWord.allAmount(dialogManager,friday,"Solids")*1f
        val solidsSaturday =  ClearWord.allAmount(dialogManager,saturday,"Solids")*1f

        ClearWord.settingChart(solidsSunday,solidsMonday,solidsTuesday,solidsWendnesday,
            solidsThursday,solidsFriday,solidsSaturday,chartBinding,"Solid")

    }
}