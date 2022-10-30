package com.viel.babycare.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.viel.babycare.MainActivity
import com.viel.babycare.R
import com.viel.babycare.databinding.ItemDialogActionBinding
import com.viel.babycare.model.DialogAction
import kotlin.coroutines.coroutineContext

class DialogActionAdapter (
    private val dialogActions:List<DialogAction>,
    private val callback:OnDialogItemClickListener,
    private val mainActivity: MainActivity
        ) : RecyclerView.Adapter<DialogActionAdapter.Viewholder>() {
    class Viewholder(private val binding:ItemDialogActionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (dialogAction:DialogAction,mainActivity: MainActivity){
            val animAlarm = AnimationUtils.loadAnimation(mainActivity,R.anim.anim_alarm)
            val animItemView = AnimationUtils.loadAnimation(mainActivity,R.anim.anim_itemview)
            binding.tvItemTime.text = dialogAction.time
            binding.tvItemAmount.text = dialogAction.amount
            binding.tvItemTitle.text = dialogAction.title
            binding.tvItemType.text = dialogAction.type
            if (dialogAction.title == "Alarm"){
                binding.layoutItem.setBackgroundResource(R.drawable.alarm_background)
                binding.imgItemContend.setImageResource(dialogAction.img)
                binding.imgItemContend.startAnimation(animAlarm)
                binding.layoutItem.startAnimation(animItemView)
            }else{
                binding.layoutItem.setBackgroundResource(R.drawable.spline_recycleview)
                binding.imgItemContend.setImageResource(dialogAction.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemDialogActionBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        dialogActions[position].let {
            holder.bind(it, mainActivity)
        }
        holder.itemView.setOnClickListener {
            callback.onClick(position)
        }
    }

    override fun getItemCount(): Int = dialogActions.size


}

