package com.viel.babycare.dialog

import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime
import kotlin.collections.ArrayList

object BathDialog: DialogFragment() {

    val time:CTime = CTime()
    val gTime = GetTime()


    @RequiresApi(Build.VERSION_CODES.N)
    fun bathDialog(mainActivity:MainActivity, arr:ArrayList<DialogAction>,adapter: DialogActionAdapter,
                   dialogManager: DialogManager,bin:Boolean,id:Int?):Dialog{
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_bath)

        val timeCurrentBath:TextView = dialog.findViewById(R.id.tv_time_bath)
        timeCurrentBath.text = time.cTime()

        val tvTimeBath = dialog.findViewById(R.id.tv_time_bath) as TextView
        val btnSave = dialog.findViewById(R.id.btn_bath_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_bath_cancel) as Button
        val btnBin = dialog.findViewById(R.id.img_bin_bath) as ImageView

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
            val img = R.drawable.bath
            val time = tvTimeBath.text.toString()
            val dialogAction = DialogAction(img = img, title = "Bath"
                , time = time, amount = "", type = "",
                date = DateDialog.getDate())
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

        timeCurrentBath.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrentBath)
        }
        return dialog
    }

}