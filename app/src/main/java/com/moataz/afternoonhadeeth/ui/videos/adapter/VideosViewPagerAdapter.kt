package com.moataz.afternoonhadeeth.ui.videos.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moataz.afternoonhadeeth.ui.videos.view.fragment.VideosTopFragment

class VideosViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            else -> VideosTopFragment()
        }
    }
}