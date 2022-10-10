package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime

class WeightDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFillDoc  = GetFillDoc()
    @RequiresApi(Build.VERSION_CODES.N)
    fun weightDialog(mainActivity: MainActivity){
        val dialog = Dialog(mainActivity)
        dialog.setContentView(R.layout.dialog_weight)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent:TextView = dialog.findViewById(R.id.tv_weight_time)
        timeCurrent.text = time.cTime()
        dialog.show()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }

        val weight:TextView = dialog.findViewById(R.id.tv_weight_amount)
        weight.setOnClickListener {
            gFillDoc.getFillDoc(mainActivity,weight,"Enter weight","kg")
        }
    }
}