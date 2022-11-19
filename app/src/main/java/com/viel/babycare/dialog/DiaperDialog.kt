package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.db.DialogManager
import com.viel.babycare.model.DialogAction
import com.viel.babycare.progress.GetTime

object DiaperDialog {

    val time:CTime = CTime()
    val gTime = GetTime()
    private lateinit var database:DatabaseReference


    @RequiresApi(Build.VERSION_CODES.N)
    fun diaperDialog(mainActivity: MainActivity,arr:ArrayList<DialogAction>,adapter:DialogActionAdapter,
                     dialogManager: DialogManager,bin:Boolean,id:Int?):Dialog{
        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        dialog.setContentView(R.layout.dialog_diaper)

        val timeCurrent:TextView = dialog.findViewById(R.id.tv_diaper_time)
        timeCurrent.text = time.cTime()

        timeCurrent.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrent)
        }
        val tvTime = dialog.findViewById(R.id.tv_diaper_time) as TextView
        val btnSave = dialog.findViewById(R.id.btn_diaper_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_diaper_cancel) as Button

        val btnBin = dialog.findViewById(R.id.img_bin_diaper) as ImageView

        if (bin == true){
            btnBin.isVisible = true
            tvTime.setText(dialogManager.getId(id!!)[0].time)
            btnBin.setOnClickListener {
                YesNoDialog.yesNoDialog(mainActivity,arr,adapter,dialogManager,id,dialog)
            }
        }else{
            btnBin.isVisible = false
        }
        btnSave.setOnClickListener {
            val dialogAction = DialogAction(img = R.drawable.nappy
                , title = "Diaper", time = tvTime.text.toString(), amount = "", type = "",
                date = DateDialog.getDate())
            if (id == null) {
                dialogManager.addDialog(dialogAction)
                arr.clear()
                arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                adapter.notifyDataSetChanged()

                checkAddFireBase(dialogManager)
                dialog.dismiss()
            }else{
                dialogManager.updateDialog(dialogAction,id)
                arr.clear()
                arr.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                adapter.notifyDataSetChanged()

                checkUpdateFireBase(dialogManager,id)
                dialog.dismiss()
            }
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }

    private fun checkUpdateFireBase(dialogManager: DialogManager,id: Int?) {
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email

            database = Firebase.database.getReference(email!!.replace(".",""))
            val pathObject = dialogManager.getAll().indexOf(DialogAction(id = id!!)).toString()
            database.child(pathObject).setValue(dialogManager.
            getAll()[dialogManager.getAll().indexOf(DialogAction(id = id))])
        }

    }

    private fun checkAddFireBase(dialogManager: DialogManager) {
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email

            database = Firebase.database.getReference(email!!.replace(".",""))
            val pathObject = (dialogManager.getAll().size - 1).toString()

            database.child(pathObject).setValue(dialogManager.
            getAll()[dialogManager.getAll().size - 1])
        }
    }
}