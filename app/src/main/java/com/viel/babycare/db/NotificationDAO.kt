package com.viel.babycare.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.viel.babycare.model.Notification

@Dao
interface NotificationDAO {

    @Insert
    fun insertNotification(notification: Notification)

    @Query("SELECT * FROM notification")
    fun getAllNotification(): List<Notification>

    @Query("SELECT * FROM notification WHERE idNo IN (:id)")
    fun findAllById(id:Int):List<Notification>

}