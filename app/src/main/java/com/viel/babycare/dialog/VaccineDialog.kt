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
import com.viel.babycare.progress.GetTime

class VaccineDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    @RequiresApi(Build.VERSION_CODES.N)
    fun vaccineDialog(mainActivity: MainActivity){
        val dialog = Dialog(mainActivity)
        dialog.setContentView(R.layout.dialog_vaccine)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent:TextView = dialog.findViewById(R.id.tv_vaccine_time)
        timeCurrent.text = time.cTime()
        dialog.show()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }
    }
}