package com.viel.babycare.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.viel.babycare.util.Const

class ProfileHelper(
    context:Context
):SQLiteOpenHelper(context,"profile.db",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""CREATE TABLE ${Const.TABLE_PROFILE}(
                           ${Const.COL_PRO_ID} INTEGER,
                           ${Const.COL_GENDER} TEXT,
                           ${Const.COL_NAME} TEXT,
                           ${Const.COL_DAY} INTEGER,
                           ${Const.COL_MONTH} INTEGER,
                           ${Const.COL_YEAR} INTEGER)""")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}