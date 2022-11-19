package com.viel.babycare.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.viel.babycare.R
import com.viel.babycare.SettingProfileActivity
import com.viel.babycare.adapter.OnButtonDateListener
import com.viel.babycare.db.ProfileManager
import com.viel.babycare.progress.CaculateAge

class DateDialogDigital(
    private val callback:OnButtonDateListener
){
    private val caculateAge:CaculateAge = CaculateAge()
    private val wD = WarningDialog()

    fun dateDialogDigital(context:SettingProfileActivity,day:Int,month:Int,year:Int,tv:TextView){
        val dateDialogDigital = Dialog(context)
        dateDialogDigital.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dateDialogDigital.setCanceledOnTouchOutside(false)
        dateDialogDigital.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dateDialogDigital.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        dateDialogDigital.setContentView(R.layout.dialog_date_digital)
        dateDialogDigital.show()

        val btnSave = dateDialogDigital.findViewById(R.id.btn_settime_digital) as Button
        val datePicker= dateDialogDigital.findViewById(R.id.dp_digital) as DatePicker
        datePicker.updateDate(year,month,day)

        btnSave.setOnClickListener {
            val pickDay = datePicker.dayOfMonth
            val pickMonth = datePicker.month+1
            val pickYear = datePicker.year

            if (caculateAge.caculateAge(pickDay,pickMonth,pickYear) == null){
                wD.warningDialog(context,dateDialogDigital,1)
            }
            tv.setText("$pickDay/$pickMonth/$pickYear")
            callback.onButtonDateClick(pickDay,pickMonth,pickYear)
            dateDialogDigital.dismiss()
        }
    }
}