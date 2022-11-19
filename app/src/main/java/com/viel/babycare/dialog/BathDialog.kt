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
import com.bumptech.glide.Glide
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
import kotlin.collections.ArrayList

object BathDialog: DialogFragment() {

    val time:CTime = CTime()
    val gTime = GetTime()
    private lateinit var database: DatabaseReference


    @RequiresApi(Build.VERSION_CODES.N)
    fun bathDialog(mainActivity:MainActivity, arr:ArrayList<DialogAction>,adapter: DialogActionAdapter,
                   dialogManager: DialogManager,bin:Boolean,id:Int?):Dialog{

        val dialog = Dialog(mainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        dialog.setContentView(R.layout.dialog_bath)

        val timeCurrentBath:TextView = dialog.findViewById(R.id.tv_time_bath)
        timeCurrentBath.text = time.cTime()

        val tvTimeBath = dialog.findViewById(R.id.tv_time_bath) as TextView
        val btnSave = dialog.findViewById(R.id.btn_bath_save) as Button
        val btnCancel = dialog.findViewById(R.id.btn_bath_cancel) as Button
        val btnBin = dialog.findViewById(R.id.img_bin_bath) as ImageView

        if (bin == true){
            btnBin.isVisible = true
            timeCurrentBath.setText(dialogManager.getId(id!!)[0].time)
                btnBin.setOnClickListener {
                    YesNoDialog.yesNoDialog(mainActivity,arr,adapter,dialogManager,id,dialog)
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

        timeCurrentBath.setOnClickListener {
            gTime.getTime(mainActivity,timeCurrentBath)
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