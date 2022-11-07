package com.viel.babycare

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.viel.babycare.databinding.ActivityScreenStartBinding
import com.viel.babycare.db.ProfileManager
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
        val profileManager = ProfileManager(this)
        val time:Timer = Timer()
        val start1 = Intent(this,SettingProfileActivity::class.java)
        val start = Intent(this, MainActivity::class.java)
        if (profileManager.getProfile().size!=0) {
            time.schedule(
                timerTask {
                    startActivity(start)
                    finish()
                }, 2000)
        }else{
            time.schedule(
                timerTask {
                    start1.putExtra("hide",1)
                    startActivity(start1)
                    finish()
                }, 2000)
        }
    }
}