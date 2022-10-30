package com.viel.babycare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.databinding.FragmentAnalysisBinding
import com.viel.babycare.dialog.DateDialog
import com.viel.babycare.progress.*


class AnalysisFragment:Fragment() {
    lateinit var binding: FragmentAnalysisBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAnalysisBinding.inflate(inflater,container,false)
        ChartSolid.chartSolid(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.solidChart)

        ChartBottle.chartBottle(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.bottleChart)

        ChartHeight.chartHeight(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.heightChart)

        ChartWeight.chartWeight(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.weightChart)

        ChartTemp.chartTemp(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.tempChart)



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.btnRefresh.setOnClickListener {
            val animRefresh = AnimationUtils.loadAnimation(context,R.anim.anim_refresh)
            binding.btnRefresh.startAnimation(animRefresh)
            ChartSolid.chartSolid(DateDialog.getYear1(),DateDialog.getMonth1(),DateDialog.getDay1(),
                DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.solidChart)

            ChartBottle.chartBottle(DateDialog.getYear1(),DateDialog.getMonth1(),DateDialog.getDay1(),
                DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.bottleChart)

            ChartHeight.chartHeight(DateDialog.getYear1(),DateDialog.getMonth1(),DateDialog.getDay1(),
                DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.heightChart)

            ChartWeight.chartWeight(DateDialog.getYear1(),DateDialog.getMonth1(),DateDialog.getDay1(),
                DateDialog.getDayOfWeek(),HomeFragment.dialogManager, binding.weightChart)

            ChartTemp.chartTemp(DateDialog.getYear1(),DateDialog.getMonth1(),DateDialog.getDay1(),
                DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.tempChart)
        }
    }
}