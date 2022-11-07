package com.viel.babycare.dialog

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction

object RemindDialog {
    fun remindDialog(mainActivity: MainActivity, dialogManager: DialogManager){
        val remindDialog = Dialog(mainActivity)
        remindDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        remindDialog.setCanceledOnTouchOutside(false)
        remindDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        remindDialog.setContentView(R.layout.dialog_remind)
        remindDialog.show()

        val tvNote = remindDialog.findViewById(R.id.tv_note_remind) as TextView
        val btnRemind = remindDialog.findViewById(R.id.btn_remind) as Button

        tvNote.setText(dialogManager.getAlarmDialog()[0].amount)
        btnRemind.setOnClickListener {
            dialogManager.deleteAlarmDialog()
            remindDialog.dismiss()
        }
    }
}