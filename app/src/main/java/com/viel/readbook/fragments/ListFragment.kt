package com.viel.readbook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viel.readbook.databinding.FragmentAcountBinding
import com.viel.readbook.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binnding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binnding = FragmentListBinding.inflate(inflater,container,false)
        return binnding.root
    }
}