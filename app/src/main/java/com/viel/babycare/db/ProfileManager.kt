package com.viel.babycare.db

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viel.babycare.model.DialogAction
import com.viel.babycare.model.Profile
import com.viel.babycare.util.Const

class ProfileManager(ctx:Context){
    private val profileHelper = ProfileHelper(ctx)

    fun addProfile(profile: Profile){
        if (getProfile().size == 0) {
            val db = profileHelper.writableDatabase
            val values = ContentValues()
            values.put(Const.COL_GENDER, profile.gender)
            values.put(Const.COL_NAME, profile.name)
            values.put(Const.COL_DAY, profile.dayOfBirth)
            values.put(Const.COL_MONTH, profile.monthOfBirth)
            values.put(Const.COL_YEAR, profile.yearOfBirth)
            db.insert(Const.TABLE_PROFILE, null, values)
        }else{
            deleteProfile()
            val db = profileHelper.writableDatabase
            val values = ContentValues()
            values.put(Const.COL_GENDER, profile.gender)
            values.put(Const.COL_NAME, profile.name)
            values.put(Const.COL_DAY, profile.dayOfBirth)
            values.put(Const.COL_MONTH, profile.monthOfBirth)
            values.put(Const.COL_YEAR, profile.yearOfBirth)
            db.insert(Const.TABLE_PROFILE, null, values)
        }
    }

    fun deleteProfile(){
        val db = profileHelper.writableDatabase
        db.delete(Const.TABLE_PROFILE,null, null)
    }

    fun getProfile(): List<Profile> {
        val profiles = arrayListOf<Profile>()
        val db = profileHelper.readableDatabase
        val cursor = db.query(Const.TABLE_PROFILE, null, null,null, null, null, null, null)
        if (cursor != null) {
            val colIdProIndex = cursor.getColumnIndex(Const.COL_PRO_ID)
            val colGenderIndex = cursor.getColumnIndex(Const.COL_GENDER)
            val colNameIndex = cursor.getColumnIndex(Const.COL_NAME)
            val colDayIndex = cursor.getColumnIndex(Const.COL_DAY)
            val colMothIndex = cursor.getColumnIndex(Const.COL_MONTH)
            val colYearIndex = cursor.getColumnIndex(Const.COL_YEAR)
            while (cursor.moveToNext()) {
                val idPro = cursor.getInt(colIdProIndex)
                val gender = cursor.getString(colGenderIndex)
                val name = cursor.getString(colNameIndex)
                val day = cursor.getInt(colDayIndex)
                val moth = cursor.getInt(colMothIndex)
                val year = cursor.getInt(colYearIndex)

                val profile = Profile(idPro, gender, name, day, moth, year)
                profiles.add(profile)
            }
        }
        return profiles
    }
}