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
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.databinding.DialogBottleBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime
import java.lang.StringBuilder

object BottleDialog{

    val time:CTime = CTime()
    val gTime:GetTime = GetTime()
    val gFillDoc:GetFillDoc = GetFillDoc()

    @RequiresApi(Build.VERSION_CODES.N)
    fun bottleDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter:DialogActionAdapter
                     ,dialogManager: DialogManager,bin:Boolean,id:Int?):Dialog{

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

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }

        val tvTime = dialog.findViewById(R.id.edt_bottle_time) as TextView
        val tvAmount = dialog.findViewById(R.id.tv_bottle_amount) as TextView
        val btnSave = dialog.findViewById(R.id.btn_bottle_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_bottle_cacel) as Button

        val btnBin = dialog.findViewById(R.id.img_bin_bottle) as ImageView

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
            val type = spinner.selectedItem.toString()
            val dialogAction = DialogAction(img = R.drawable.baby_bottle,
                title = "Milk Botle",
                time = tvTime.text.toString(),
                amount = tvAmount.text.toString(),
                type = type, date = DateDialog.getDate())
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