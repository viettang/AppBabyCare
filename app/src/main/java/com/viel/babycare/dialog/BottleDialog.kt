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
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime
import java.lang.StringBuilder

object BottleDialog{

    val time:CTime = CTime()
    val gTime:GetTime = GetTime()
    val gFillDoc:GetFillDoc = GetFillDoc()

    @RequiresApi(Build.VERSION_CODES.N)
    fun bottleDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter:DialogActionAdapter):Dialog{

        val dialog = Dialog(mainActivity)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_bottle)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val timeCurrent = dialog.findViewById(R.id.edt_bottle_time) as TextView
        timeCurrent.text = time.cTime()

        val amount = dialog.findViewById(R.id.tv_bottle_amount) as TextView
        amount.setOnClickListener {
            gFillDoc.getFillDoc(mainActivity,amount,"Enter amount","ml")
        }

        val spinner: Spinner = dialog.findViewById(R.id.spn_main)
        ArrayAdapter.createFromResource(mainActivity, R.array.bottles_array,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        val type = spinner.selectedItem.toString()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }

        val tvTime = dialog.findViewById(R.id.edt_bottle_time) as TextView
        val tvAmount = dialog.findViewById(R.id.tv_bottle_amount) as TextView
        val btnSave = dialog.findViewById(R.id.btn_bottle_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_bottle_cacel) as Button
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(R.drawable.baby_bottle,
                "Milk Botle",
                tvTime.text.toString(),
                tvAmount.text.toString(),
                type)
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