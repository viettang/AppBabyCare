package com.viel.babycare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification")
data class Notification(

    @PrimaryKey(autoGenerate = true)
    val idNo:Int=0,
    val imgNo:Int,
    val noteNo:String,
    val dateNo:String,
    val timeNo:String
)
