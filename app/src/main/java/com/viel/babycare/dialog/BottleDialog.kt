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
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.databinding.DialogBottleBinding
import com.viel.babycare.progress.GetFillDoc
import com.viel.babycare.progress.GetTime
import java.lang.StringBuilder

class BottleDialog{

    val time:CTime = CTime()
    val gTime:GetTime = GetTime()
    val gFillDoc:GetFillDoc = GetFillDoc()

    @RequiresApi(Build.VERSION_CODES.N)
    fun bottleDialog(mainActivity: MainActivity){

        val dialog = Dialog(mainActivity)

        dialog.setContentView(R.layout.dialog_bottle)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val timeCurrent = dialog.findViewById(R.id.edt_bottle_time) as TextView
        timeCurrent.text = time.cTime()

        val amount = dialog.findViewById(R.id.tv_bottle_amount) as TextView
        amount.setOnClickListener {
            gFillDoc.getFillDoc(mainActivity,amount,"Enter amount","ml")
        }

        val spinner: Spinner = dialog.findViewById(R.id.spn_main)
        ArrayAdapter.createFromResource(mainActivity, R.array.planets_array,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        dialog.show()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }
    }

}