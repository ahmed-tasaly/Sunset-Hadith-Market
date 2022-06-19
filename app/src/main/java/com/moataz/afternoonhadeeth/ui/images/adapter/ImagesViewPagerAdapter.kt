package com.moataz.afternoonhadeeth.ui.images.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moataz.afternoonhadeeth.ui.images.view.fragment.ImagesKabaFragment
import com.moataz.afternoonhadeeth.ui.images.view.fragment.ImagesMasjidFragment
import com.moataz.afternoonhadeeth.ui.images.view.fragment.ImagesNabiFragment
import com.moataz.afternoonhadeeth.ui.images.view.fragment.ImagesWallpaperFragment

class ImagesViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ImagesWallpaperFragment()
            1 -> ImagesKabaFragment()
            2 -> ImagesMasjidFragment()
            else -> ImagesNabiFragment()
        }
    }
}