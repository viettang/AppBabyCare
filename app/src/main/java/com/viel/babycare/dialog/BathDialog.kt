package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.fragments.HomeFragment
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import kotlin.collections.ArrayList

object BathDialog: DialogFragment() {

    val time:CTime = CTime()
    val gTime = GetTime()


    @RequiresApi(Build.VERSION_CODES.N)
    fun bathDialog(mainActivity:MainActivity, arr:ArrayList<DialogAction>,adapter: DialogActionAdapter):Dialog{
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_bath)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val timeCurrentBath:TextView = dialog.findViewById(R.id.tv_time_bath)
        timeCurrentBath.text = time.cTime()

        val tvTimeBath = dialog.findViewById(R.id.tv_time_bath) as TextView
        val btnSave = dialog.findViewById(R.id.btn_bath_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_bath_cancel) as Button
        btnSave.setOnClickListener {
            val img = R.drawable.bath
            val time = tvTimeBath.text.toString()
            val dialogAction = DialogAction(img,"Bath"
                ,time,"","")
            arr.add(dialogAction)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
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