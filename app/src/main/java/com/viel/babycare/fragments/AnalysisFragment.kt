package com.viel.babycare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viel.babycare.databinding.FragmentAnalysisBinding
import com.viel.babycare.dialog.DateDialog
import com.viel.babycare.progress.*


class AnalysisFragment:Fragment() {
    private lateinit var binding: FragmentAnalysisBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAnalysisBinding.inflate(inflater,container,false)
        ChartSolid.chartSolid(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.solidChart)

        ChartBottle.chartBottle(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.bottleChart)

        ChartHeight.chartHeight(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.heightChart)

        ChartWeight.chartWeight(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.weightChart)

        ChartTemp.chartTemp(DateDialog.getYear(),DateDialog.getMonth(),DateDialog.getDay(),
        DateDialog.getDayOfWeek(),HomeFragment.dialogManager,binding.tempChart)

        return binding.root
    }
}