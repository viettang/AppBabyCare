package com.viel.babycare.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viel.babycare.R
import com.viel.babycare.databinding.ActivitySettingProfileBinding

class SettingProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySettingProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}