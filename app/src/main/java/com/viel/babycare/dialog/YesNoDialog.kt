package com.viel.babycare.dialog

import android.app.Dialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.widget.Button
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction

object YesNoDialog {
    fun yesNoDialog(mainActivity: MainActivity, arr:ArrayList<DialogAction>, adapter: DialogActionAdapter,
                    dialogManager: DialogManager, id:Int?,dialog: Dialog){
        val yesNoDialog = Dialog(mainActivity)
        yesNoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        yesNoDialog.setCanceledOnTouchOutside(false)
        yesNoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        yesNoDialog.setContentView(R.layout.dialog_yes_no)
        yesNoDialog.show()

        val btn_yes = yesNoDialog.findViewById(R.id.btn_yes) as Button
        val btn_no = yesNoDialog.findViewById(R.id.btn_no) as Button
        btn_yes.setOnClickListener {
            dialogManager.deleteDialog(id!!)
            arr.clear()
            arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
            adapter.notifyDataSetChanged()
            yesNoDialog.dismiss()
            dialog.dismiss()
        }
        btn_no.setOnClickListener {
            yesNoDialog.dismiss()
        }
    }
}