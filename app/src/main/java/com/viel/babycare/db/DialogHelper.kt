package com.viel.babycare.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.viel.babycare.util.Const

class DialogHelper(
    context:Context
):SQLiteOpenHelper(context,"dialog.db",null,2) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""CREATE TABLE ${Const.TABLE_DIALOG}(
                             ${Const.COL_ID} INTEGER PRIMARY KEY AUTOINCREMENT, 
                             ${Const.COL_IMG} INTEGER,
                             ${Const.COL_TITLE} TEXT, 
                             ${Const.COL_TIME} TEXT, 
                             ${Const.COL_AMOUNT} TEXT, 
                             ${Const.COL_TYPE} TEXT, 
                             ${Const.COL_DATE})""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Const.TABLE_DIALOG}")
        onCreate(db)
    }
}