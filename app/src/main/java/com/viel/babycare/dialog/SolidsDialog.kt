package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.Window
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
import com.viel.babycare.progress.GetFill
import com.viel.babycare.progress.GetTime
import kotlin.math.log

object SolidsDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFill = GetFill()

    @RequiresApi(Build.VERSION_CODES.N)
    fun solidsDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter: DialogActionAdapter,
                     dialogManager: DialogManager
                     ,bin:Boolean,id:Int?):Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_solids)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentSolids: TextView = dialog.findViewById(R.id.tv_solids_time)
        timeCurrentSolids.text = time.cTime()

        timeCurrentSolids.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrentSolids)
        }

        val tvAmount: TextView = dialog.findViewById(R.id.tv_solids_amount)
        tvAmount.text = "1ml"
        tvAmount.setOnClickListener {
            gFill.getFill(mainActivity, tvAmount, "g", "oz", "ml", "tsp")
        }
        val btnSave = dialog.findViewById(R.id.btn_solids_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_solids_cancel) as Button
        val tvName = dialog.findViewById(R.id.edt_solid_name) as EditText

        val btnBin = dialog.findViewById(R.id.img_bin_solids) as ImageView

        if (bin == true){
            btnBin.isVisible = true
            timeCurrentSolids.setText(dialogManager.getId(id!!)[0].time)
            tvName.setText(dialogManager.getId(id!!)[0].amount)
            tvAmount.setText(dialogManager.getId(id!!)[0].type)
            btnBin.setOnClickListener {
                YesNoDialog.yesNoDialog(mainActivity,arr,adapter,dialogManager,id,dialog)
            }
        }else{
            btnBin.isVisible = false
        }
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(img = R.drawable.feed,
                title = "Solids",
                time = timeCurrentSolids.text.toString(),
                amount = tvName.text.toString(),
                type = tvAmount.text.toString(),date = DateDialog.getDate())
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