package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.databinding.DialogBottleBinding
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetFill
import com.viel.babycare.progress.GetTime
import java.lang.StringBuilder

object MedicineDialog{

    val time:CTime = CTime()
    val gTime:GetTime = GetTime()
    val gFill:GetFill = GetFill()

    @RequiresApi(Build.VERSION_CODES.N)
    fun medicineDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter:DialogActionAdapter):Dialog {

        val dialog = Dialog(mainActivity)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_medicine)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val medicineAmount = dialog.findViewById(R.id.tv_medicine_amount) as TextView
        val timeCurrent = dialog.findViewById(R.id.tv_medicine_time) as TextView
        timeCurrent.text = time.cTime()


        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity, timeCurrent)
        }
        medicineAmount.setOnClickListener {
            gFill.getFill(mainActivity, medicineAmount, "pill", "oz", "drop", "ml")
        }
        val tvTime = dialog.findViewById(R.id.tv_medicine_time) as TextView
        val tvName = dialog.findViewById(R.id.tv_medicine_name) as TextView
        val btnSave = dialog.findViewById(R.id.btn_medicine_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_medicine_cancel) as Button
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(R.drawable.medicine,
                "Medicine",
                tvTime.text.toString(),
                tvName.text.toString(),
                medicineAmount.text.toString())
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