package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.progress.GetFill
import com.viel.babycare.progress.GetTime

class SolidsDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFill = GetFill()

    @RequiresApi(Build.VERSION_CODES.N)
    fun solidsDialog(mainActivity: MainActivity){
        val dialog = Dialog(mainActivity)
        dialog.setContentView(R.layout.dialog_solids)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentBath:TextView = dialog.findViewById(R.id.tv_solids_time)
        timeCurrentBath.text = time.cTime()
        dialog.show()

        timeCurrentBath.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrentBath)
        }

        val tvAmount:TextView = dialog.findViewById(R.id.tv_solids_amount)
        tvAmount.text = "1.0"
        tvAmount.setOnClickListener {
            gFill.getFill(mainActivity,tvAmount,"g","oz","ml","tsp")
        }
    }
}