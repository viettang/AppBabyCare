package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime

object TempDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFillDoc  = GetFillDoc()
    @RequiresApi(Build.VERSION_CODES.N)
    fun tempDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter: DialogActionAdapter,
                   dialogManager: DialogManager
                   ,bin:Boolean,id:Int?):Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_temp)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent: TextView = dialog.findViewById(R.id.tv_temp_time)
        timeCurrent.text = time.cTime()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrent)
        }

        val temp: TextView = dialog.findViewById(R.id.tv_temp_amount)
        temp.setOnClickListener {
            gFillDoc.getFillDoc(mainActivity, temp, "Enter temp", "℃")
        }
        val btnSave = dialog.findViewById(R.id.btn_temp_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_temp_cancel) as Button

        val btnBin = dialog.findViewById(R.id.img_bin_temp) as ImageView

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
            val dialogAction = DialogAction(img = R.drawable.temp,
                title = "Temp",
                time = timeCurrent.text.toString(),
                amount = temp.text.toString(),
                type = "",dayOfWeek = DateDialog.getDayOfWeek(), day = DateDialog.getDate(),
                mounth = DateDialog.getMonth(), year = DateDialog.getYear())
            if (id == null) {
                dialogManager.addDialog(dialogAction)
                arr.add(dialogAction)
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