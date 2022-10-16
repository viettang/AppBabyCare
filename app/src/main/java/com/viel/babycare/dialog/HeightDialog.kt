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
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime

object HeightDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFillDoc  = GetFillDoc()
    @RequiresApi(Build.VERSION_CODES.N)
    fun heightDialog(mainActivity: MainActivity, arr:ArrayList<DialogAction>,
                     adapter: DialogActionAdapter):Dialog{
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_height)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrent:TextView = dialog.findViewById(R.id.tv_height_time)
        timeCurrent.text = time.cTime()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }

        val height:TextView = dialog.findViewById(R.id.tv_height_amount)
        height.setOnClickListener {
            gFillDoc.getFillDoc(mainActivity,height,"Enter height","cm")
        }
        val tvTime = dialog.findViewById(R.id.tv_height_time) as TextView
        val tvHeight = dialog.findViewById(R.id.tv_height_amount) as TextView
        val btnSave = dialog.findViewById(R.id.btn_height_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_height_cancel) as Button
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(R.drawable.height
                ,"Height",tvTime.text.toString(),tvHeight.text.toString(),"")
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