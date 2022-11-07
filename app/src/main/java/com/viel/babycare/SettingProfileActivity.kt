package com.viel.babycare

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.viel.babycare.adapter.OnButtonDateListener
import com.viel.babycare.databinding.ActivitySettingProfileBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.db.ProfileManager
import com.viel.babycare.dialog.DateDialog
import com.viel.babycare.dialog.DateDialogDigital
import com.viel.babycare.model.DialogAction
import com.viel.babycare.model.Profile


class SettingProfileActivity : AppCompatActivity(),OnButtonDateListener {
    private lateinit var binding:ActivitySettingProfileBinding
    var day:Int = DateDialog.getDay()
    private var month:Int = DateDialog.getMonth()
    private var year:Int = DateDialog.getYear()
    private var name:String =""
    private var gender:String = ""
    private lateinit var profileManager: ProfileManager
    private lateinit var dateDialogDigital:DateDialogDigital
    private lateinit var dialogManager: DialogManager
    private var isHide = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isHide = intent.getIntExtra("hide",0)
        if (isHide == 1){
            binding.tvProfile.isVisible = false
            binding.imgBack.isVisible = false
            binding.btnDelProfile.isVisible = false
        }else{
            binding.tvProfile.isVisible = true
            binding.imgBack.isVisible = true
            binding.btnDelProfile.isVisible = true
        }

        profileManager = ProfileManager(this)
        dialogManager = DialogManager(this)

        binding.imgBack.setOnClickListener {
            finish()
        }

        if (isHide==0){
            binding.edtProfileName.hint = "${profileManager.getProfile()[0].name}"

            binding.edtProfileBirthday.hint = "${profileManager.getProfile()[0].dayOfBirth}/" +
                    "${profileManager.getProfile()[0].monthOfBirth}/${profileManager.getProfile()[0].yearOfBirth}"
        }

        binding.ivProfileBoy.setOnClickListener {
            binding.ivProfileBoy.setBackgroundResource(R.drawable.spline_chose)
            binding.ivProfileGirl.setBackgroundResource(R.drawable.spline)
            gender = "boy"
            binding.imgProfileGender.setImageResource(R.drawable.baby_boy)
        }

        binding.ivProfileGirl.setOnClickListener {
            binding.ivProfileGirl.setBackgroundResource(R.drawable.spline_chose)
            binding.ivProfileBoy.setBackgroundResource(R.drawable.spline)
            gender = "girl"
            binding.imgProfileGender.setImageResource(R.drawable.baby_girl)
        }

        dateDialogDigital = DateDialogDigital(this)
        binding.edtProfileBirthday.setOnClickListener {
            dateDialogDigital.dateDialogDigital(this,day,month,year,binding.edtProfileBirthday)
        }

        binding.tvSaveProfile.setOnClickListener {
            name = binding.edtProfileName.text.toString()
            profileManager.addProfile(Profile(gender = gender, name = name,
                dayOfBirth = day, monthOfBirth = month, yearOfBirth = year))
            if (isHide==0) {
                finish()
            }else{
                val intent:Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnDelProfile.setOnClickListener {
            profileManager.deleteProfile()
            dialogManager.deleteAllDialog()

            val intent:Intent = Intent(this,ScreenStart::class.java)
            startActivity(intent)
        }

    }

    override fun onButtonDateClick(day: Int, month: Int, year: Int) {
        this.day =day
        this.month = month
        this.year = year
    }
}