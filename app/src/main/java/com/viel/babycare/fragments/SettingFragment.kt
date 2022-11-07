package com.viel.babycare.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.SettingProfileActivity
import com.viel.babycare.databinding.FragmentSettingBinding
import com.viel.babycare.db.ProfileManager
import com.viel.babycare.model.Profile
import com.viel.babycare.model.ProfileLiveData
import com.viel.babycare.progress.CaculateAge
import kotlin.concurrent.fixedRateTimer

class SettingFragment:Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var settingProfileActivity: SettingProfileActivity
    private lateinit var profileManager: ProfileManager
    private val cA = CaculateAge()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater,container,false)

        settingProfileActivity = SettingProfileActivity()
        profileManager = ProfileManager(context as MainActivity)
        binding.clEditProfile.setOnClickListener {
            val i = Intent(requireContext(), SettingProfileActivity::class.java)
            startActivity(i)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.tvNameUser.text = profileManager.getProfile()[0].name

        if (profileManager.getProfile()[0].gender == "boy"){
            binding.imgGender.setImageResource(R.drawable.baby_boy)
        }else{
            binding.imgGender.setImageResource(R.drawable.baby_girl)
        }

        val ageDay = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(0)
        val ageWeek = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(1)
        val ageMonth = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(2)
        val ageYear = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(3)

        binding.tvAge.text = "$ageWeek Week"

    }

}