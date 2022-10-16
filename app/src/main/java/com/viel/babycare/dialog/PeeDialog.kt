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

object PeeDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    @RequiresApi(Build.VERSION_CODES.N)
    fun peeDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter: DialogActionAdapter):Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_pee)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent: TextView = dialog.findViewById(R.id.tv_pee_time)
        timeCurrent.text = time.cTime()
        val isChangeDiaper: CheckBox = dialog.findViewById(R.id.cb_change_diaper)

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrent)
        }
        val tvTime = dialog.findViewById(R.id.tv_pee_time) as TextView
        val btnSave = dialog.findViewById(R.id.btn_pee_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_pee_cancel) as Button
        btnSave.setOnClickListener {
            val isCheck: String
            if (isChangeDiaper.isChecked) {
                isCheck = "Changed diaper"
            } else {
                isCheck = "Not change diaper"
            }
            val dialogAction =
                DialogAction(R.drawable.ic_baby_pee, "Pee", tvTime.text.toString(), isCheck, "")
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