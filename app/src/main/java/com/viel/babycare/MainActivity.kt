package com.viel.babycare

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator
import com.viel.babycare.adapter.BabyPagerAdapter
import com.viel.babycare.adapter.DialogActionAdapter
import com.viel.babycare.adapter.OnButtonClickListener
import com.viel.babycare.databinding.ActivityMainBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.dialog.*
import com.viel.babycare.fragments.HomeFragment
import java.util.*


open class MainActivity : AppCompatActivity(){

    private lateinit var binding:ActivityMainBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabTitles = arrayOf("Home","Analysis","Setting")
        val tabIcon = arrayOf(R.drawable.ic_homedis,R.drawable.ic_analysisdis,R.drawable.ic_settingdis)
        binding.vpMain.adapter = BabyPagerAdapter(this)
        binding.tlMain.tabSelectedIndicator
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
                R.id.baby_bottle -> BottleDialog.bottleDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.baby_sleep -> SleepDialog.sleepDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.baby_bath -> BathDialog.bathDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.pee -> PeeDialog.peeDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.baby_feed -> SolidsDialog.solidsDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.baby_medicine -> MedicineDialog.medicineDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.baby_vaccine ->  VaccineDialog.vaccineDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.nappy -> DiaperDialog.diaperDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.weight -> WeightDialog.weightDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.height -> HeightDialog.heightDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()
                R.id.temp -> TempDialog.tempDialog(this,
                    HomeFragment.dialogActions,HomeFragment.adapter,HomeFragment.dialogManager,false,null).show()

            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }
        binding.tvDate.setText("${DateDialog.change(DateDialog.getDayOfWeek())}, ${DateDialog.getDay()}/${DateDialog.getMonth()+1}/${DateDialog.getYear()}")
        binding.tvDate.setOnClickListener { DateDialog.dateDialog(this,HomeFragment.dialogActions,HomeFragment.adapter,
            HomeFragment.dialogManager,binding.tvDate).show() }

        binding.fabAlarm.setOnClickListener {
            AlarmDialog.alarmDialog(this,HomeFragment.dialogActions,HomeFragment.adapter,
                HomeFragment.dialogManager,false,null).show()
        }

        createNotificationChanel()


    }

    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name:CharSequence = "babycareReminderChannel"
            val description = "channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("babycare",name,importance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }

}