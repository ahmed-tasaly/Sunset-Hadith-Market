package com.moataz.afternoonhadeeth.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moataz.afternoonhadeeth.ui.view.fragment.*

class ImagesViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ImagesWallpaperFragment()
            1 -> ImagesNabiFragment()
            2 -> ImagesMasjidFragment()
            3 -> ImagesKabaFragment()
            else -> ImagesAqsaFragment()
        }
    }
}