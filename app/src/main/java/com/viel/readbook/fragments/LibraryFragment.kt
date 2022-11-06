package com.viel.readbook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viel.readbook.databinding.FragmentAcountBinding
import com.viel.readbook.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private lateinit var binnding: FragmentLibraryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binnding = FragmentLibraryBinding.inflate(inflater,container,false)
        return binnding.root
    }
}