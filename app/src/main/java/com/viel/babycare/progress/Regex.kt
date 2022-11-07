package com.viel.babycare.progress

import android.content.ContentValues.TAG
import android.util.Log

object Regex {
    fun regex(string: String): ArrayList<Int> {
        val number = arrayListOf<Int>()
        val dt = string.split(":").toTypedArray()
        number.add(dt[0].toInt())
        number.add(dt[1].toInt())
        Log.d(TAG, "regex: sxkqdbjqwudqwuhdwqhhdh${number[0]}/${number[1]}")
        return number
    }
}