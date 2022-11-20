package com.viel.babycare

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.viel.babycare.adapter.BabyPagerAdapter
import com.viel.babycare.adapter.OnButtonClickListener
import com.viel.babycare.databinding.ActivityMainBinding
import com.viel.babycare.db.DialogManager
import com.viel.babycare.db.ProfileManager
import com.viel.babycare.dialog.*
import com.viel.babycare.fragments.HomeFragment
import com.viel.babycare.model.AlarmReceiver
import com.viel.babycare.progress.CaculateAge
import com.viel.babycare.progress.Regex
import java.util.*
import kotlin.math.log


open class MainActivity : AppCompatActivity(),OnButtonClickListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent
    private val alarm:AlarmDialog = AlarmDialog(this)
    private val profileManager: ProfileManager = ProfileManager(this)
    private val cA = CaculateAge()
    private val dialogManager:DialogManager = DialogManager(this)

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WaitDialog.waitDialog(this)

        val ageWeek = cA.caculateAge(profileManager.getProfile()[0].dayOfBirth,profileManager.
        getProfile()[0].monthOfBirth,profileManager.getProfile()[0].yearOfBirth)
            ?.get(1)

        val tabTitles = arrayOf("Home","Analysis","Setting")
        val tabIcon = arrayOf(R.drawable.ic_homedis,R.drawable.ic_analysisdis,R.drawable.ic_settingdis)
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

//        binding.navMain.setItemBackgroundResource(R.color.black)
//        binding.navMain.itemTextColor = ColorStateList.valueOf(Color.WHITE)
//        binding.tlMain.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
//        binding.tlMain.tabIconTint = ColorStateList.valueOf(Color.WHITE)
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
            alarm.alarmDialog(this,HomeFragment.dialogActions,HomeFragment.adapter,
                HomeFragment.dialogManager,false,null).show()
        }


        createNotificationChanel()

        binding.imgBell.setOnClickListener {
            val intent = Intent(this,NotificationActivity::class.java)
            startActivity(intent)
        }



    }

    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val soundUri:Uri = Uri.parse("android.resource://${packageName}/${R.raw.mama}")
            val attributes:AudioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            val name:CharSequence = "babycareReminderChannel"
            val description = "channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("babycare",name,importance)
            channel.description = description
            channel.setSound(soundUri,attributes)

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }

    override fun onButtonClick() {
        calendar = Calendar.getInstance()
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this,AlarmReceiver::class.java)
        val position = dialogManager.getAlarmDialog().size-1
        intent.putExtra("note","${dialogManager.getAlarmDialog()[position].amount}")

        val setTime = dialogManager.getAlarmDialog()[position].time

        calendar[Calendar.DAY_OF_MONTH] = DateDialog.getDay1()
        calendar[Calendar.MONTH] = DateDialog.getMonth1()
        calendar[Calendar.YEAR] = DateDialog.getYear1()
        calendar[Calendar.HOUR_OF_DAY] = Regex.regex(setTime)[0]
        calendar[Calendar.MINUTE] = Regex.regex(setTime)[1]
        calendar[Calendar.MILLISECOND] = 0
        calendar[Calendar.SECOND] = 0
        pendingIntent = PendingIntent.getBroadcast(this,position,intent,
            PendingIntent.FLAG_MUTABLE)
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
    }
}



