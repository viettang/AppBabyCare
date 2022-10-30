package com.viel.babycare.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime

object AlarmDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    @RequiresApi(Build.VERSION_CODES.N)
    fun alarmDialog(mainActivity: MainActivity, arr:ArrayList<DialogAction>, adapter: DialogActionAdapter,
                    dialogManager: DialogManager,bin:Boolean,id:Int?
    ):Dialog{

        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_alarm)

        val tvTimeAlarm = dialog.findViewById(R.id.tv_chose_time_alarm) as TextView
        tvTimeAlarm.text = time.cTime()
        tvTimeAlarm.setOnClickListener {
            gTime.getTime(mainActivity,tvTimeAlarm)
        }

        val btnBin = dialog.findViewById(R.id.img_bin_alarm) as ImageView
        if (bin == true){
            btnBin.isVisible = true
            btnBin.setOnClickListener {
                YesNoDialog.yesNoDialog(mainActivity,arr,adapter,dialogManager,id,dialog)
            }
        }else{
            btnBin.isVisible = false
        }

        val edtAlarm = dialog.findViewById(R.id.edt_note) as EditText
        val btnSave = dialog.findViewById(R.id.btn_save_alarm) as Button
        val btnCancel = dialog.findViewById(R.id.btn_cancel_alarm) as Button
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(img = R.drawable.alarm, title = "Alarm", time = tvTimeAlarm.text.toString(),
                amount = edtAlarm.text.toString(), type = "", date = DateDialog.getDate())

            if (id == null) {
                dialogManager.addDialog(dialogAction)
                arr.clear()
                arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                dialogManager.updateDialog(dialogAction,id)
                arr.clear()
                arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }
}