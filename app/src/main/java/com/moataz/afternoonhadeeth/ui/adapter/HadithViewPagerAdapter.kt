package com.moataz.afternoonhadeeth.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moataz.afternoonhadeeth.ui.view.fragment.HadithTopFragment
import com.moataz.afternoonhadeeth.ui.view.fragment.VideosChoosenFragment

class HadithViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HadithTopFragment()
            else -> VideosChoosenFragment()
        }
    }
}