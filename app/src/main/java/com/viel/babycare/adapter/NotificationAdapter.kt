package com.viel.babycare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viel.babycare.databinding.ItemNotificationBinding
import com.viel.babycare.model.Notification

class NotificationAdapter(
    private val notifications:List<Notification>
): RecyclerView.Adapter<NotificationAdapter.Viewholder>() {
    class Viewholder(private val binding: ItemNotificationBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification){
            binding.imgItemNotification.setImageResource(notification.imgNo)
            binding.tvItemNew.text = notification.noteNo
            binding.tvItemTimeHour.text = notification.timeNo
            binding.tvItemTimeDate.text = notification.dateNo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        notifications[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = notifications.size
}