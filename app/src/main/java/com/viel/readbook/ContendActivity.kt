package com.viel.readbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viel.readbook.databinding.ActivityContendBinding
import com.viel.readbook.model.Chuoi

class ContendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val chuoi = Chuoi()
        binding.tvContend.text = chuoi.tring

    }
}