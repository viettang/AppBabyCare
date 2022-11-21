package com.viel.babycare.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.viel.babycare.*
import com.viel.babycare.databinding.FragmentSettingBinding
import com.viel.babycare.db.ProfileManager
import com.viel.babycare.dialog.FeedBackDialog
import com.viel.babycare.dialog.RateDialog
import com.viel.babycare.model.Profile
import com.viel.babycare.progress.CaculateAge
import com.viel.babycare.progress.RequestHelper
import java.net.URI
import java.util.jar.Manifest

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

        binding.btnLogin.setOnClickListener {
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }

        settingProfileActivity = SettingProfileActivity()
        profileManager = ProfileManager(context as MainActivity)
        binding.clEditProfile.setOnClickListener {
            val i = Intent(requireContext(), SettingProfileActivity::class.java)
            startActivity(i)
        }

        ArrayAdapter.createFromResource(context as MainActivity,R.array.inotation_array,android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnProfile.adapter = adapter
        }

        when(profileManager.getProfile()[0].inotation){
            "Week" -> binding.spnProfile.setSelection(0,true)
            "Month" -> binding.spnProfile.setSelection(1,true)
            "Year" -> binding.spnProfile.setSelection(2,true)
        }


        binding.imgUser.setOnClickListener {
            val user = Firebase.auth.currentUser
            user?.let {
                val intent = Intent(requireContext(),UpdateProfileActivity::class.java)
                startActivity(intent)
            }

        }



        binding.clSignOut.setOnClickListener {
            Firebase.auth.signOut()
            val intent= Intent(context,ScreenStart::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            HomeFragment.dialogActions.clear()
            startActivity(intent)
        }

        binding.clRateUs.setOnClickListener {
            RateDialog.rateDialog(requireContext())
        }

        binding.clFeedBack.setOnClickListener {
            FeedBackDialog.feedBackDialog(requireContext())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val user = Firebase.auth.currentUser
        user?.let {
            val userName = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            binding.btnLogin.isVisible = false

            binding.tvUser2.text = userName
            binding.tvUser.text = email
            Glide.with(this).load(photoUrl).error(R.drawable.ic_user).into(binding.imgUser)
        }
        hideSignOut(user)


        binding.tvNameUser.text = profileManager.getProfile()[0].name

        if (profileManager.getProfile()[0].gender == "boy"){
            binding.imgGender.setImageResource(R.drawable.baby_boy)
        }else{
            binding.imgGender.setImageResource(R.drawable.baby_girl)
        }

        val ageWeek = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(1)
        val ageMonth = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(2)
        val ageYear = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(3)

        binding.tvAge.text = "$ageMonth Month"
        when(profileManager.getProfile()[0].inotation){
            "Week" -> binding.tvAge.text = "$ageWeek Week"
            "Month" -> binding.tvAge.text = "$ageMonth Month"
            "Year" -> binding.tvAge.text = "$ageYear Year"
        }

        binding.spnProfile.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val gender = profileManager.getProfile()[0].gender
                val name = profileManager.getProfile()[0].name
                val dOB = profileManager.getProfile()[0].dayOfBirth
                val mOB = profileManager.getProfile()[0].monthOfBirth
                val yOB = profileManager.getProfile()[0].yearOfBirth
                profileManager.addProfile(Profile(gender = gender, name = name, dayOfBirth = dOB,
                    monthOfBirth = mOB, yearOfBirth = yOB, inotation = parent?.getItemAtPosition(position)
                        .toString()))

                when(parent?.getItemAtPosition(position).toString()){
                    "Week" -> binding.tvAge.text = "$ageWeek Week"
                    "Month" -> binding.tvAge.text = "$ageMonth Month"
                    "Year" -> binding.tvAge.text = "$ageYear Year"
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
    private fun hideSignOut(user: FirebaseUser?) {
        binding.clSignOut.isVisible = user != null
    }

}


