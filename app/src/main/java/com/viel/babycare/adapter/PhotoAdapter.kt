package com.viel.babycare.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.viel.babycare.R
import com.viel.babycare.databinding.ItemPhotoBinding
import com.viel.babycare.model.Photo

class PhotoAdapter(
    private val photos:List<Photo>
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo){
            binding.imgPhoto.setImageResource(photo.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        photos[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = photos.size

}