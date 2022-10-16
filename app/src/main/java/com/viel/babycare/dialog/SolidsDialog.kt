package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetFill
import com.viel.babycare.progress.GetTime

object SolidsDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    val gFill = GetFill()

    @RequiresApi(Build.VERSION_CODES.N)
    fun solidsDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter: DialogActionAdapter):Dialog {
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_solids)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentBath: TextView = dialog.findViewById(R.id.tv_solids_time)
        timeCurrentBath.text = time.cTime()

        timeCurrentBath.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrentBath)
        }

        val tvAmount: TextView = dialog.findViewById(R.id.tv_solids_amount)
        tvAmount.text = "1.0"
        tvAmount.setOnClickListener {
            gFill.getFill(mainActivity, tvAmount, "g", "oz", "ml", "tsp")
        }
        val btnSave = dialog.findViewById(R.id.btn_solids_ok) as Button
        val btnCancel = dialog.findViewById(R.id.btn_solids_cancel) as Button
        val tvName = dialog.findViewById(R.id.edt_solid_name) as EditText
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(R.drawable.feed,
                "Solids",
                timeCurrentBath.text.toString(),
                tvName.text.toString(),
                tvAmount.text.toString())
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