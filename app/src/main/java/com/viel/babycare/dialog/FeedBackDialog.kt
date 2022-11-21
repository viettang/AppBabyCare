package com.viel.babycare.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import com.viel.babycare.R

object FeedBackDialog {

    fun feedBackDialog(context: Context){

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        dialog.setContentView(R.layout.dialog_feed_back)
        dialog.show()

        val btnSubmit = dialog.findViewById(R.id.btn_feedback_sumit) as Button
        val btnCancel = dialog.findViewById(R.id.btn_feedback_cancel) as Button

        btnSubmit.setOnClickListener {
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}