package com.viel.babycare.progress

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.slider.Slider
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import java.lang.StringBuilder

class GetFillDoc {

    fun getFillDoc(mainActivity: MainActivity,currentAmount:TextView,title:String,measurement:String){
        val dialog = Dialog(mainActivity)

        dialog.setContentView(R.layout.dialog_filldoc)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val tvTitle:TextView = dialog.findViewById(R.id.tv_filldoc)
        val tvamount:TextView = dialog.findViewById(R.id.tv_x)
        val amount:EditText = dialog.findViewById(R.id.edt_x)
        val save:Button = dialog.findViewById(R.id.btn_x)

        tvTitle.text = title
        tvamount.text = measurement
        save.setOnClickListener {
            currentAmount.text = StringBuilder().append(amount.text.toString()).append(" ").append(tvamount.text.toString())
            dialog.dismiss()
        }

    }
}