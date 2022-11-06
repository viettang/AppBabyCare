package com.viel.readbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viel.readbook.databinding.ItemBookBinding
import com.viel.readbook.model.Book

class BookAdapter (
    private val books: List<Book>,
    private val callback:BookItemClickListener
        ) : RecyclerView.Adapter<BookAdapter.ViewHolder>(){
    class ViewHolder (private val binding:ItemBookBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book){
            binding.tvName.text = book.name
            binding.tvChaper.text = book.chaper
            binding.tvDate.text = book.date
            binding.imgCover.setImageResource(book.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        return ViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        books[position].let {
            holder.bind(it)
        }
        holder.itemView.setOnClickListener {
            callback.onBookItemClick(position)
        }
    }

    override fun getItemCount(): Int = books.size
}