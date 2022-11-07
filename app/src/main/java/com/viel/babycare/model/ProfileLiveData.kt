package com.viel.babycare.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viel.babycare.SettingProfileActivity

class ProfileLiveData:ViewModel() {

    val listProfileLiveData: MutableLiveData<List<Profile>> by lazy {
        MutableLiveData<List<Profile>>()
    }
    private lateinit var settingProfileActivity: SettingProfileActivity
    private lateinit var list: ArrayList<Profile>

    init {
        profileViewModel()
    }

    fun getProfileLiveData(): MutableLiveData<List<Profile>> {
        return listProfileLiveData
    }

    fun profileViewModel() {
        settingProfileActivity = SettingProfileActivity()
        list = ArrayList()
        listProfileLiveData.value = list
    }

    fun addProfile(profile: Profile) {
        list.add(0, profile)
        listProfileLiveData.value = list
    }
}