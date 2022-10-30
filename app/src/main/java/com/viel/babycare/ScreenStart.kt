package com.viel.babycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.viel.babycare.databinding.ActivityScreenStartBinding
import java.util.*
import kotlin.concurrent.timerTask

class ScreenStart : AppCompatActivity() {
    private lateinit var binding:ActivityScreenStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animLeft = AnimationUtils.loadAnimation(this,R.anim.anim_alpha)
        val animRight = AnimationUtils.loadAnimation(this,R.anim.anim_beta)
        binding.flBabycare.startAnimation(animLeft)
        binding.imgBabycare.startAnimation(animRight)
        startMain()
    }

    private fun startMain() {
        val time:Timer = Timer()
        val start = Intent(this, MainActivity::class.java)
        time.schedule(
            timerTask {
                startActivity(start)
                finish()
            },2000)
    }
}