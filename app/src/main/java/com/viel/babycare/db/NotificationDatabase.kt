package com.viel.babycare.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viel.babycare.model.Notification

@Database(entities = [Notification::class], version = 1, exportSchema = true)
abstract class NotificationDatabase: RoomDatabase() {
    companion object {
        private val DATABASE_NAME: String = "notification.db"

        @Volatile
        private var instance:NotificationDatabase? = null

        fun getInstance(context: Context): NotificationDatabase {
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext,
                        NotificationDatabase::class.java,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }
    }



    abstract fun notificationDao(): NotificationDAO
}