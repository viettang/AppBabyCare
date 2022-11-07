package com.viel.babycare.model

import java.io.Serializable

data class Profile(
    val id:Int=0,
    val gender:String,
    val name: String,
    val dayOfBirth:Int,
    val monthOfBirth:Int,
    val yearOfBirth:Int,
) : Serializable
