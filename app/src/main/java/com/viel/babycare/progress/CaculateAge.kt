package com.viel.babycare.progress

import com.viel.babycare.dialog.DateDialog
import java.time.Month

class CaculateAge {
    fun caculateAge(day:Int,month: Int,year:Int): ArrayList<Int>? {
        val caculateDay:Int = if (day == DateDialog.getDay() && month == DateDialog.getMonth()+1
            && year == DateDialog.getYear()){
            0
        }else if (day < DateDialog.getDay() && month == DateDialog.getMonth()+1
            && year == DateDialog.getYear()){
            DateDialog.getDay() - day
        }else if (month < DateDialog.getMonth()+1
            && year == DateDialog.getYear()){
            30 - day + DateDialog.getDay() + 30*(DateDialog.getMonth()-month)
        }else if (year < DateDialog.getYear()){
            30 - day + DateDialog.getDay() + 30*(12-month + DateDialog.getMonth()) + 365*(DateDialog.getYear()-year-1)
        }else{
            -1
        }

        val caculateWeek:Int = caculateDay/7
        val caculateMonth:Int = caculateDay/30
        val caculateYear:Int = caculateDay/365

        val caculated = arrayListOf<Int>(caculateDay,caculateWeek,caculateMonth,caculateYear)

        if (caculateDay<0){
            return null
        }else{
            return caculated
        }
    }
}