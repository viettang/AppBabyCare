package com.viel.readbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.viel.readbook.adapter.BookAdapter
import com.viel.readbook.adapter.BookPagerAdapter
import com.viel.readbook.databinding.ActivityMainBinding
import com.viel.readbook.model.Book

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabTitles = arrayOf("Home","List","Library","Acount")
        val tabIcon = arrayOf(R.drawable.ic__home,R.drawable.ic_list,R.drawable.ic_booklibrary,R.drawable.ic_account)
        binding.vpBook.adapter = BookPagerAdapter(this)
        TabLayoutMediator(binding.tlBook,binding.vpBook){
            tab,position -> tab.text = tabTitles[position]
            tab.setIcon(tabIcon[position])
        }.attach()
    }
}