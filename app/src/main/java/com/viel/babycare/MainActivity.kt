package com.viel.babycare

import android.app.Activity
import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.viel.babycare.adapter.BabyPagerAdapter
import com.viel.babycare.databinding.ActivityMainBinding
import com.viel.babycare.dialog.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    val bDialog:BottleDialog = BottleDialog()
    val sDialog:SleepDialog = SleepDialog()
    val baDialog:BathDialog = BathDialog()
    val pDialog:PeeDialog = PeeDialog()
    val slDialog:SolidsDialog = SolidsDialog()
    val mDialog:MedicineDialog = MedicineDialog()
    val vDialog:VaccineDialog = VaccineDialog()
    val dDialog:DiaperDialog = DiaperDialog()
    val wDialog:WeightDialog = WeightDialog()
    val hDialog:HeightDialog = HeightDialog()
    val tDialog:TempDialog = TempDialog()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabTitles = arrayOf("Home","Analysis","Setting")
        val tabIcon = arrayOf(R.drawable.ic_home,R.drawable.ic_analysis,R.drawable.ic_setting)
        binding.vpMain.adapter = BabyPagerAdapter(this)
        TabLayoutMediator(binding.tlMain,binding.vpMain){
            tab,position -> tab.text = tabTitles[position]
            tab.setIcon(tabIcon[position])
        }.attach()


        binding.home.setOnClickListener {
           if (binding.drawer.isDrawerOpen(GravityCompat.START)){
               binding.drawer.closeDrawer(GravityCompat.START)
           }else{
               binding.drawer.openDrawer(GravityCompat.START)
           }
        }

        binding.navMain.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.baby_bottle -> bDialog.bottleDialog(this)
                R.id.baby_sleep -> sDialog.sleepDialog(this)
                R.id.baby_bath -> baDialog.bathDialog(this)
                R.id.pee -> pDialog.peeDialog(this)
                R.id.baby_feed -> slDialog.solidsDialog(this)
                R.id.baby_medicine -> mDialog.medicineDialog(this)
                R.id.baby_vaccine -> vDialog.vaccineDialog(this)
                R.id.nappy -> dDialog.diaperDialog(this)
                R.id.weight -> wDialog.weightDialog(this)
                R.id.height -> hDialog.heightDialog(this)
                R.id.temp -> tDialog.tempDialog(this)

            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

}