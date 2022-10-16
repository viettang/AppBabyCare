package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime

object SleepDialog {

    val time: CTime = CTime()
    val gTime = GetTime()

    @RequiresApi(Build.VERSION_CODES.N)
    fun sleepDialog(
        mainActivity: MainActivity,
        arr: ArrayList<DialogAction>,
        adapter: DialogActionAdapter
    ): Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_sleep)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentSleep: TextView = dialog.findViewById(R.id.tv_fall_sleep)
        val timeCurrentWakeup: TextView = dialog.findViewById(R.id.tv_wake_up)
        timeCurrentSleep.text = time.cTime()
        timeCurrentWakeup.text = time.cTime()

        timeCurrentSleep.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrentSleep)
        }
        timeCurrentWakeup.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrentWakeup)
        }
        val btnSave = dialog.findViewById(R.id.btn_sleep_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_sleep_cancel) as Button
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(R.drawable.baby_sleep,
                "Sleep",
                timeCurrentSleep.text.toString(),
                "Wake up: ${timeCurrentWakeup.text.toString()}",
                "")
            arr.add(dialogAction)
            adapter.notifyDataSetChanged()
        }
        return dialog
    }
}