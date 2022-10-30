package com.viel.babycare.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viel.babycare.MainActivity
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.adapter.OnDialogItemClickListener
import com.viel.babycare.databinding.FragmentHomeBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.dialog.*
import com.viel.babycare.model.DialogAction
import kotlinx.coroutines.*

class HomeFragment:Fragment(),OnDialogItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    companion object{
     val dialogActions = ArrayList<DialogAction>()
    lateinit var adapter : DialogActionAdapter
    lateinit var dialogManager: DialogManager
    }

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        adapter = DialogActionAdapter(dialogActions,this, context as MainActivity)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false)
        dialogManager = DialogManager(context as MainActivity)
        dialogActions.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
        adapter.notifyDataSetChanged()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(position: Int) {
        val dialogAction = dialogActions[position]
        when(dialogAction.title){
            "Bath" -> BathDialog.bathDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Milk Botle" -> BottleDialog.bottleDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Diaper" -> DiaperDialog.diaperDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Height" -> HeightDialog.heightDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Medicine" -> MedicineDialog.medicineDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Pee" -> PeeDialog.peeDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Sleep" -> SleepDialog.sleepDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Solids" -> SolidsDialog.solidsDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Temp" -> TempDialog.tempDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id
            ).show()
            "Vaccine" -> VaccineDialog.vaccineDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Weight" -> WeightDialog.weightDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()
            "Alarm" -> AlarmDialog.alarmDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()


        }
    }

}