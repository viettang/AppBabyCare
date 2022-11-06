package com.viel.readbook.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.viel.readbook.fragments.AcountFragment
import com.viel.readbook.fragments.HomeFragment
import com.viel.readbook.fragments.LibraryFragment
import com.viel.readbook.fragments.ListFragment

class BookPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->HomeFragment()
            1 ->ListFragment()
            2 ->LibraryFragment()
            3 ->AcountFragment()
            else -> throw IllegalArgumentException("NO FRAGMENT $position")
        }
    }
}