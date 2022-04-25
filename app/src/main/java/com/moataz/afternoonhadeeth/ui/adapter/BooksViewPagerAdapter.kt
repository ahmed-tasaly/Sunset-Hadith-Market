package com.moataz.afternoonhadeeth.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moataz.afternoonhadeeth.ui.view.fragment.BooksHadithFragment
import com.moataz.afternoonhadeeth.ui.view.fragment.BooksSerahFragment
import com.moataz.afternoonhadeeth.ui.view.fragment.BooksTopFragment
import com.moataz.afternoonhadeeth.ui.view.fragment.VideosChoosenFragment

class BooksViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BooksTopFragment()
            1 -> BooksSerahFragment()
            else -> BooksHadithFragment()
        }
    }
}