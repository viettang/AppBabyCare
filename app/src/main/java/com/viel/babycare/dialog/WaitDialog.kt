package com.viel.babycare.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.viel.babycare.R
import java.util.*

object WaitDialog:DialogFragment() {

    fun waitDialog(context: Context){
        val dialogWait = Dialog(context)
        dialogWait.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogWait.setCanceledOnTouchOutside(false)
        dialogWait.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogWait.setContentView(R.layout.dialog_wait)

        val imgWait = dialogWait.findViewById(R.id.img_wait) as ImageView
        val animation = AnimationUtils.loadAnimation(context,R.anim.anim_refresh)
        imgWait.startAnimation(animation)
        dialogWait.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialogWait.dismiss()
        },2800)
    }
}