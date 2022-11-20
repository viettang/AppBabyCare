package com.viel.babycare.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

    private lateinit var database: DatabaseReference

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        dialogManager = DialogManager(context as MainActivity)
        dialogActions.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
        adapter = DialogActionAdapter(dialogActions,this, context as MainActivity)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false)

        listPhoto = getListPhotos() as ArrayList<Photo>
        photoAdapter = PhotoAdapter(listPhoto)
        binding.vpIndicator.adapter = photoAdapter
        binding.ciIndicator.setViewPager(binding.vpIndicator)
        binding.vpIndicator.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mHandler.removeCallbacks(mRunable)
                mHandler.postDelayed(mRunable,3000)
            }
        })

            synDataFirebase(adapter)

        return binding.root
    }


    private fun synDataFirebase(adapter: DialogActionAdapter) {
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            database = Firebase.database.getReference(email!!.replace(".",""))
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dialogManager.deleteAlarmAcceptDialog("Alarm")
                    dialogActions.clear()
                    for (postSnapshot in dataSnapshot.children) {
                        val dialogAction = postSnapshot.getValue(DialogAction::class.java)
                        dialogAction?.let {
                            dialogManager.addDialog(dialogAction)
                        }
                    }
                    dialogActions.addAll(dialogManager.getFinterDialog(DateDialog.getDate()))
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
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


