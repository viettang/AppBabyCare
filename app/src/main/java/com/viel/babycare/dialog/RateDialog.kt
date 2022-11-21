package com.viel.babycare.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.viel.babycare.R

object RateDialog {

    @SuppressLint("SetTextI18n")
    fun rateDialog(context: Context){

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes!!.windowAnimations = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        dialog.setContentView(R.layout.dialog_rate)

        val noteRate = dialog.findViewById(R.id.tv_note_rate) as TextView
        val btnRate = dialog.findViewById(R.id.btn_rate) as Button
        val star1 = dialog.findViewById(R.id.imv_star_1) as ImageView
        val star2 = dialog.findViewById(R.id.imv_star_2) as ImageView
        val star3 = dialog.findViewById(R.id.imv_star_3) as ImageView
        val star4 = dialog.findViewById(R.id.imv_star_4) as ImageView
        val star5 = dialog.findViewById(R.id.imv_star_5) as ImageView
        val animStar =  dialog.findViewById(R.id.anim_star) as ImageView

        star1.isVisible = false
        star2.isVisible = false
        star3.isVisible = false
        star4.isVisible = false
        star5.isVisible = false
        btnRate.setOnClickListener {
            dialog.dismiss()
        }
        noteRate.text = "We are working hard for a better user experience. " +
                "We'd greatly appreciate if you can rate us."

        Handler(Looper.getMainLooper()).postDelayed(
            {star1.isVisible = true
             star2.isVisible = true
             star3.isVisible = true
             star4.isVisible = true
             star5.isVisible = true
             animStar.isVisible = false},
            2000)

        star1.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star)
            star2.setImageResource(R.drawable.ic_star_white)
            star3.setImageResource(R.drawable.ic_star_white)
            star4.setImageResource(R.drawable.ic_star_white)
            star5.setImageResource(R.drawable.ic_star_white)
        }
        star2.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star)
            star2.setImageResource(R.drawable.ic_star)
            star3.setImageResource(R.drawable.ic_star_white)
            star4.setImageResource(R.drawable.ic_star_white)
            star5.setImageResource(R.drawable.ic_star_white)
        }
        star3.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star)
            star2.setImageResource(R.drawable.ic_star)
            star3.setImageResource(R.drawable.ic_star)
            star4.setImageResource(R.drawable.ic_star_white)
            star5.setImageResource(R.drawable.ic_star_white)
        }
        star4.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star)
            star2.setImageResource(R.drawable.ic_star)
            star3.setImageResource(R.drawable.ic_star)
            star4.setImageResource(R.drawable.ic_star)
            star5.setImageResource(R.drawable.ic_star_white)
        }
        star5.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star)
            star2.setImageResource(R.drawable.ic_star)
            star3.setImageResource(R.drawable.ic_star)
            star4.setImageResource(R.drawable.ic_star)
            star5.setImageResource(R.drawable.ic_star)
        }

        dialog.show()

    }
}