package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime

object VaccineDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    @RequiresApi(Build.VERSION_CODES.N)
    fun vaccineDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter:DialogActionAdapter):Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_vaccine)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent: TextView = dialog.findViewById(R.id.tv_vaccine_time)
        timeCurrent.text = time.cTime()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrent)
        }
        val btnSave = dialog.findViewById(R.id.btn_vaccin_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_vaccin_cancel) as Button
        val tvName = dialog.findViewById(R.id.tv_vaccin_name) as TextView
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(R.drawable.vaccine,
                timeCurrent.text.toString(),
                "Vaccine",
                "Vaccine name: ${tvName.text.toString()}",
                "")
            arr.add(dialogAction)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }
}