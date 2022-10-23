package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime

object WeightDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFillDoc  = GetFillDoc()
    @RequiresApi(Build.VERSION_CODES.N)
    fun weightDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter:DialogActionAdapter,
                     dialogManager: DialogManager
                     ,bin:Boolean,id:Int?):Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_weight)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent: TextView = dialog.findViewById(R.id.tv_weight_time)
        timeCurrent.text = time.cTime()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrent)
        }

        val weight: TextView = dialog.findViewById(R.id.tv_weight_amount)
        weight.setOnClickListener {
            gFillDoc.getFillDoc(mainActivity, weight, "Enter weight", "kg")
        }
        val btnSave = dialog.findViewById(R.id.btn_weight_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_weight_cancel) as Button

        val btnBin = dialog.findViewById(R.id.img_bin_weight) as ImageView

        if (bin == true){
            btnBin.isVisible = true
            btnBin.setOnClickListener {
                dialogManager.deleteDialog(id!!)
                arr.clear()
                arr.addAll(dialogManager.getAllDialog())
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }else{
            btnBin.isVisible = false
        }
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(img = R.drawable.weight,
                title = "Weight",
                time = timeCurrent.text.toString(),
                amount = weight.text.toString(),
                type = "", date = DateDialog.getDate())
            if (id == null) {
                dialogManager.addDialog(dialogAction)
                arr.clear()
                arr.addAll(dialogManager.getAllDialog())
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                dialogManager.updateDialog(dialogAction,id)
                arr.clear()
                arr.addAll(dialogManager.getAllDialog())
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