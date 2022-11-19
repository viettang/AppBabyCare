package com.viel.babycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viel.babycare.adapter.NotificationAdapter
import com.viel.babycare.db.NotificationDatabase
import com.viel.babycare.databinding.ActivityNotificationBinding
import com.viel.babycare.model.Notification

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNotificationBinding
    private val notifications = ArrayList<Notification>()
    private lateinit var adapter : NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NotificationAdapter(notifications)

        notifications.addAll(NotificationDatabase.getInstance(this).notificationDao().getAllNotification())

        binding.rvNotification.adapter = adapter
        binding.rvNotification.layoutManager = LinearLayoutManager(this,
            RecyclerView.VERTICAL,false)

        binding.imgBackNotification.setOnClickListener {
            finish()
        }
    }
}