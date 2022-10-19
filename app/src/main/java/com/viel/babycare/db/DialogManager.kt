package com.viel.babycare.db

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import com.viel.babycare.model.DialogAction
import com.viel.babycare.util.Const

class DialogManager(ctx:Context) {
    private val dialogHelper = DialogHelper(ctx)

    fun addDialog(dialogAction: DialogAction){
        val db = dialogHelper.writableDatabase
        val values = ContentValues()
        values.put(Const.COL_IMG,dialogAction.img)
        values.put(Const.COL_TIME,dialogAction.time)
        values.put(Const.COL_TITLE,dialogAction.title)
        values.put(Const.COL_AMOUNT,dialogAction.amount)
        values.put(Const.COL_TYPE,dialogAction.type)
        values.put(Const.COL_DAYOFWEEK,dialogAction.dayOfWeek)
        values.put(Const.COL_DAY,dialogAction.day)
        values.put(Const.COL_MONTH,dialogAction.mounth)
        values.put(Const.COL_YEAR,dialogAction.year)
        db.insert(Const.TABLE_DIALOG,null,values)
    }

    fun updateDialog(dialogAction: DialogAction,id:Int){
        val db = dialogHelper.writableDatabase
        val values = ContentValues()
        values.put(Const.COL_IMG,dialogAction.img)
        values.put(Const.COL_TIME,dialogAction.time)
        values.put(Const.COL_TITLE,dialogAction.title)
        values.put(Const.COL_AMOUNT,dialogAction.amount)
        values.put(Const.COL_TYPE,dialogAction.type)
        values.put(Const.COL_DAYOFWEEK,dialogAction.dayOfWeek)
        values.put(Const.COL_DAY,dialogAction.day)
        values.put(Const.COL_MONTH,dialogAction.mounth)
        values.put(Const.COL_YEAR,dialogAction.year)
        db.update(Const.TABLE_DIALOG,values,"${Const.COL_ID}=$id", null)
    }

    fun deleteDialog(id:Int ){
        val db = dialogHelper.writableDatabase
        db.delete(Const.TABLE_DIALOG,"${Const.COL_ID}=?", arrayOf(id.toString()))
    }

    fun getAllDialog():List<DialogAction> {
        val dialogActions = arrayListOf<DialogAction>()
        val db = dialogHelper.readableDatabase
        val cursor = db.query(Const.TABLE_DIALOG, null, null, null, null, null, null, null)
        if (cursor != null) {
            val colIdIndex = cursor.getColumnIndex(Const.COL_ID)
            val colImgIndex = cursor.getColumnIndex(Const.COL_IMG)
            val colTimeIndex = cursor.getColumnIndex(Const.COL_TIME)
            val colTitleIndex = cursor.getColumnIndex(Const.COL_TITLE)
            val colAmountIndex = cursor.getColumnIndex(Const.COL_AMOUNT)
            val colTypeIndex = cursor.getColumnIndex(Const.COL_TYPE)
            val colDayOfWeekIndex = cursor.getColumnIndex(Const.COL_DAYOFWEEK)
            val colDayIndex = cursor.getColumnIndex(Const.COL_DAY)
            val colMonthIndex = cursor.getColumnIndex(Const.COL_MONTH)
            val colYearIndex = cursor.getColumnIndex(Const.COL_YEAR)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(colIdIndex)
                val img = cursor.getInt(colImgIndex)
                val time = cursor.getString(colTimeIndex)
                val title = cursor.getString(colTitleIndex)
                val amount = cursor.getString(colAmountIndex)
                val type = cursor.getString(colTypeIndex)
                val dayOfWeek = cursor.getInt(colDayOfWeekIndex)
                val day = cursor.getInt(colDayIndex)
                val month = cursor.getInt(colMonthIndex)
                val year = cursor.getInt(colYearIndex)

                dialogActions.add(DialogAction(id,
                    img,
                    title,
                    time,
                    amount,
                    type,
                    dayOfWeek,
                    day,
                    month,
                    year))
            }
        }
        return dialogActions
    }

}