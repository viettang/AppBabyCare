package com.viel.babycare.progress

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.renderer.YAxisRenderer
import com.github.mikephil.charting.utils.ColorTemplate
import com.viel.babycare.db.DialogManager

object ClearWord {
    fun clearWord(amount: String): Int {
        val dAmount = amount.filter { it.isDigit() }
        val number = dAmount.toInt()
        return number
    }

    fun allAmount(dialogManager: DialogManager, day: String, title: String): Int {
        val arr = dialogManager.getChart(day)
        var total = 0
        for (i in 0..arr.size - 1) {
            if (title == "Solids") {
                if(arr[i].title == "Solids") {
                    total += clearWord(arr[i].type)
                }
            }
            if (title == "Bottle") {
                if(arr[i].title == "Milk Botle") {
                    total += clearWord(arr[i].amount)
                }
            }
            if (title == "Height") {
                if(arr[i].title == "Height") {
                    total = clearWord(arr[i].amount)
                }
            }
            if (title == "Temp") {
                if(arr[i].title == "Temp") {
                    total = clearWord(arr[i].amount)
                }
            }
            if (title == "Weight") {
                if(arr[i].title == "Weight") {
                    total = clearWord(arr[i].amount)
                }
            }
        }
        return total
    }

    fun settingChart(sun:Float,mon:Float,tue:Float,wend:Float,thur:Float,fri:Float,sat:Float,chartBinding:BarChart,lable:String){
        var barList:ArrayList<BarEntry> = ArrayList()
        var barDataSet: BarDataSet
        var barData:BarData

        barList.add(BarEntry(0.5f,sun))
        barList.add(BarEntry(1.5f,mon))
        barList.add(BarEntry(2.5f,tue))
        barList.add(BarEntry(3.5f,wend))
        barList.add(BarEntry(4.5f,thur))
        barList.add(BarEntry(5.5f,fri))
        barList.add(BarEntry(6.5f,sat))
        barDataSet = BarDataSet(barList,lable)
        val months = arrayOf<String>("Sunday","Monday", "Tusday", "Wendnesday", "Thursday","Friday","Saturday")
        barData = BarData(barDataSet)
        chartBinding.isAutoScaleMinMaxEnabled = true
        chartBinding.data = barData
        chartBinding.animateY(2000)

        val xAxis: XAxis =  chartBinding.getXAxis()
        xAxis.valueFormatter = IndexAxisValueFormatter(months)
        xAxis.setLabelCount(7)
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = 7f
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS,250)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 10f

    }
}