package com.viel.babycare.progress

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R

class GetTime {
    @RequiresApi(Build.VERSION_CODES.M)
    fun getTime(mainActivity: MainActivity, currentTime: TextView){
        val dialog = Dialog(mainActivity)

        dialog.setContentView(R.layout.dialog_time)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val timePicker: TimePicker = dialog.findViewById(R.id.t_time)
        timePicker.setIs24HourView(true)
        val save: Button =dialog.findViewById(R.id.btn_save_time)
        save.setOnClickListener {
            currentTime.text = "${timePicker.hour} : ${timePicker.minute}"
            dialog.dismiss()
        }
    }
}