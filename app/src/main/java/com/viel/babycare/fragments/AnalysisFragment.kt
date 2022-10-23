package com.viel.babycare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viel.babycare.databinding.FragmentAnalysisBinding
import com.viel.babycare.model.DialogAction


class AnalysisFragment:Fragment() {
    private lateinit var binding: FragmentAnalysisBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAnalysisBinding.inflate(inflater,container,false)
        return binding.root
    }

}