package com.viel.babycare.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.databinding.FragmentHomeBinding
import com.viel.babycare.dialog.*
import com.viel.babycare.model.DialogAction
import kotlinx.coroutines.*

class HomeFragment:Fragment() {
    private lateinit var binding: FragmentHomeBinding
    companion object{
     val dialogActions = ArrayList<DialogAction>()
    lateinit var adapter : DialogActionAdapter}

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        adapter = DialogActionAdapter(dialogActions)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false)
        BathDialog.bathDialog(activity as MainActivity,dialogActions,adapter)
        BathDialog.bathDialog(activity as MainActivity, dialogActions, adapter)
        DiaperDialog.diaperDialog(activity as MainActivity, dialogActions, adapter)
        HeightDialog.heightDialog(activity as MainActivity, dialogActions, adapter)
        MedicineDialog.medicineDialog(activity as MainActivity, dialogActions, adapter)
        PeeDialog.peeDialog(activity as MainActivity, dialogActions, adapter)
        SleepDialog.sleepDialog(activity as MainActivity, dialogActions, adapter)
        SolidsDialog.solidsDialog(activity as MainActivity, dialogActions, adapter)
        TempDialog.tempDialog(activity as MainActivity, dialogActions, adapter)
        VaccineDialog.vaccineDialog(activity as MainActivity, dialogActions, adapter)
        WeightDialog.weightDialog(activity as MainActivity, dialogActions, adapter)
        return binding.root
    }

}