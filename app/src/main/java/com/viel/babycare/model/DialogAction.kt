package com.viel.babycare.model

import android.widget.ImageView
import android.widget.TextView

data class DialogAction(
    val id:Int = -1,
    val img: Int = 0,
    val title:String="",
    val time: String="",
    val amount:String="",
    val type:String="",
    val dayOfWeek:Int=0,
    val day:Int=0,
    val mounth:Int=0,
    val year:Int=0
){
    override fun equals(other: Any?): Boolean {
        val temp = other as DialogAction
        return this.id == temp.id
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
