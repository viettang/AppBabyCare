package com.viel.babycare.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.SettingProfileActivity

class WarningDialog {
    fun warningDialog(context:Context, dialog: Dialog?, change:Int){
        val wDialog = Dialog(context)
        wDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        wDialog.setCanceledOnTouchOutside(false)
        wDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        wDialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        wDialog.setContentView(R.layout.dialog_warning)
        wDialog.show()

        val tvWarning = wDialog.findViewById(R.id.tv_warning) as TextView
        val btnWarning = wDialog.findViewById(R.id.btn_warning) as Button

        if (change==1){
            tvWarning.text = "Please check baby's birthday"
        }else if (change == 2){
            tvWarning.text = "Please enter the blank infomation !!!"
        }
        btnWarning.setOnClickListener {
            if (change==1|| change == 2){
                wDialog.dismiss()
            }else {
                wDialog.dismiss()
                dialog?.dismiss()
            }
        }
    }
}