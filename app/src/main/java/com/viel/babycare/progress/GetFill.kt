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

class GetFill {

    fun getFill(mainActivity: MainActivity,currentAmount:TextView,a:String,
                b:String,c:String,d:String){
        val dialog = Dialog(mainActivity)

        dialog.setContentView(R.layout.dialog_fill)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val radio1:RadioButton = dialog.findViewById(R.id.rb_g)
        val radio2:RadioButton = dialog.findViewById(R.id.rb_oz)
        val radio3:RadioButton = dialog.findViewById(R.id.rb_ml)
        val radio4:RadioButton = dialog.findViewById(R.id.rb_tsp)
        val tvamount:TextView = dialog.findViewById(R.id.tv_amount)
        val amount:EditText = dialog.findViewById(R.id.edt_amount)
        val save:Button = dialog.findViewById(R.id.btn_amount)
        val rdGroup:RadioGroup = dialog.findViewById(R.id.rg_amount)

        radio1.setText(a)
        radio2.setText(b)
        radio3.setText(c)
        radio4.setText(d)
        rdGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener(){
              gr,id-> when(id){
                   R.id.rb_g -> tvamount.setText(a)
                   R.id.rb_oz-> tvamount.setText(b)
                   R.id.rb_ml -> tvamount.setText(c)
                   R.id.rb_tsp -> tvamount.setText(d)
                   else -> tvamount.setText("?")
           }
        })

        save.setOnClickListener {
            currentAmount.text = StringBuilder().append(amount.text.toString()).append(" ").append(tvamount.text.toString())
            dialog.dismiss()
        }

    }
}