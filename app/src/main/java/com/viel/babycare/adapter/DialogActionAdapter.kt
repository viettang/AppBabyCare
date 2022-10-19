package com.viel.babycare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viel.babycare.databinding.ItemDialogActionBinding
import com.viel.babycare.model.DialogAction

class DialogActionAdapter (
    private val dialogActions:List<DialogAction>,
    private val callback:OnDialogItemClickListener
        ) : RecyclerView.Adapter<DialogActionAdapter.Viewholder>() {
    class Viewholder(private val binding:ItemDialogActionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (dialogAction:DialogAction){
            binding.imgItemContend.setImageResource(dialogAction.img)
            binding.tvItemTime.text = dialogAction.time
            binding.tvItemAmount.text = dialogAction.amount
            binding.tvItemTitle.text = dialogAction.title
            binding.tvItemType.text = dialogAction.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemDialogActionBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        dialogActions[position].let {
            holder.bind(it)
        }
        holder.itemView.setOnClickListener {
            callback.onClick(position)
        }
    }

    override fun getItemCount(): Int = dialogActions.size


}

