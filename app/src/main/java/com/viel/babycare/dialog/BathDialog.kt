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
import com.viel.babycare.progress.GetTime

class BathDialog {

    val time:CTime = CTime()
    val gTime = GetTime()

    @RequiresApi(Build.VERSION_CODES.N)
    fun bathDialog(mainActivity: MainActivity){
        val dialog = Dialog(mainActivity)
        dialog.setContentView(R.layout.dialog_bath)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentBath:TextView = dialog.findViewById(R.id.tv_time_bath)
        timeCurrentBath.text = time.cTime()
        dialog.show()

        timeCurrentBath.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrentBath)
        }

    }
}