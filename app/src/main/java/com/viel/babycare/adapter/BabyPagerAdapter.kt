package com.viel.babycare.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.viel.babycare.fragments.AnalysisFragment
import com.viel.babycare.fragments.HomeFragment
import com.viel.babycare.fragments.SettingFragment

class BabyPagerAdapter(activity: AppCompatActivity):FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> AnalysisFragment()
            2 -> SettingFragment()
            else -> throw IllegalArgumentException("NO FRAGMENT $position")
        }
    }
}