package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.adapter.OnButtonClickListener
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime
import com.viel.babycare.progress.Regex
import java.util.*
import kotlin.collections.ArrayList

class AlarmDialog(
    private val callback: OnButtonClickListener?
):DialogFragment() {

    val time:CTime = CTime()
    val gTime = GetTime()
    private val wD = WarningDialog()

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.N)
    fun alarmDialog(mainActivity: MainActivity, arr:ArrayList<DialogAction>, adapter: DialogActionAdapter,
                    dialogManager: DialogManager,bin:Boolean,id:Int?
    ):Dialog{

        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        dialog.setContentView(R.layout.dialog_alarm)


        val tvTimeAlarm = dialog.findViewById(R.id.tv_chose_time_alarm) as TextView
        tvTimeAlarm.text = time.cTime()
        tvTimeAlarm.setOnClickListener {
            gTime.getTime(mainActivity,tvTimeAlarm)
        }


        val edtAlarm = dialog.findViewById(R.id.edt_note) as EditText
        val btnSave = dialog.findViewById(R.id.btn_save_alarm) as Button
        val btnCancel = dialog.findViewById(R.id.btn_cancel_alarm) as Button
        val btnBin = dialog.findViewById(R.id.img_bin_alarm) as ImageView

        if (bin == true){
            btnBin.isVisible = true
            tvTimeAlarm.text = dialogManager.getId(id!!)[0].time
            edtAlarm.setText(dialogManager.getId(id)[0].amount)
            btnBin.setOnClickListener {
                YesNoDialog.yesNoDialog(mainActivity,arr,adapter,dialogManager,id,dialog)
            }
        }else{
            btnBin.isVisible = false
        }

        btnSave.setOnClickListener {
            val dialogAction = DialogAction(img = R.drawable.alarm, title = "Alarm", time = tvTimeAlarm.text.toString(),
                amount = edtAlarm.text.toString(), type = "", date = DateDialog.getDate())

            val hour = Regex.regex(tvTimeAlarm.text.toString())[0]
            val minute = Regex.regex(tvTimeAlarm.text.toString())[1]

            val crTime:TimePicker = TimePicker(mainActivity)


            if (id == null) {
                if (hour > crTime.hour ) {
                    dialogManager.addDialog(dialogAction)
                    arr.clear()
                    arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                    adapter.notifyDataSetChanged()
                    callback!!.onButtonClick()
                    dialog.dismiss()
                }else if (hour == crTime.hour && minute > crTime.minute){
                    dialogManager.addDialog(dialogAction)
                    arr.clear()
                    arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                    adapter.notifyDataSetChanged()
                    callback!!.onButtonClick()
                    dialog.dismiss()
                }
                else{
                    wD.warningDialog(mainActivity,dialog,0)
                }
            }else{
                if (hour > crTime.hour) {
                    dialogManager.updateDialog(dialogAction, id)
                    arr.clear()
                    arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }else if (hour == crTime.hour && minute > crTime.minute){
                    dialogManager.updateDialog(dialogAction, id)
                    arr.clear()
                    arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
                else{
                    wD.warningDialog(mainActivity,dialog,0)
                }
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }


}