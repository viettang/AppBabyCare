package com.viel.babycare.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.adapter.OnDialogItemClickListener
import com.viel.babycare.adapter.PhotoAdapter
import com.viel.babycare.databinding.FragmentHomeBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.dialog.*
import com.viel.babycare.model.DialogAction
import com.viel.babycare.model.Photo
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment:Fragment(),OnDialogItemClickListener{
    private lateinit var binding: FragmentHomeBinding
    companion object{
        val dialogActions = ArrayList<DialogAction>()
        lateinit var adapter : DialogActionAdapter
        lateinit var dialogManager: DialogManager
    }
    private lateinit var photoAdapter:PhotoAdapter
    private var listPhoto:ArrayList<Photo> = ArrayList<Photo>()
    private val mHandler = Handler(Looper.getMainLooper())
    val mRunable = Runnable {
        if (binding.vpIndicator.currentItem == listPhoto.size-1){
            binding.vpIndicator.currentItem = 0
        }else {
            binding.vpIndicator.currentItem = binding.vpIndicator.currentItem + 1
        }

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
        if (dialogActions.size == 0) {
            dialogActions.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
            adapter.notifyDataSetChanged()
        }

        listPhoto = getListPhotos() as ArrayList<Photo>
        photoAdapter = PhotoAdapter(listPhoto)
        binding.vpIndicator.adapter = photoAdapter
        binding.ciIndicator.setViewPager(binding.vpIndicator)
        binding.vpIndicator.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mHandler.removeCallbacks(mRunable)
                mHandler.postDelayed(mRunable,4500)
            }
        })


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun getListPhotos():List<Photo>{
        val list = arrayListOf<Photo>()
        list.add(Photo(R.drawable.photo_indicator_3))
        list.add(Photo(R.drawable.photo_indicator_0))
        list.add(Photo(R.drawable.photo_indicator_1))
        list.add(Photo(R.drawable.photo_indicator_2))
        return list
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(position: Int) {
        val alarm = AlarmDialog(null)
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
            "Alarm" -> alarm.alarmDialog(context as MainActivity, dialogActions, adapter,
                dialogManager,true,dialogAction.id).show()


        }

    }

}


