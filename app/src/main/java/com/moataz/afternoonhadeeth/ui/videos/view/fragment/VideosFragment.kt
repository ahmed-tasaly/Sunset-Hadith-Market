package com.moataz.afternoonhadeeth.ui.videos.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moataz.afternoonhadeeth.databinding.FragmentVideosBinding
import com.moataz.afternoonhadeeth.ui.videos.adapter.VideosViewPagerAdapter

class VideosFragment : Fragment() {

    private lateinit var binding: FragmentVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosBinding.inflate(layoutInflater)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        binding.videosViewPager.adapter =
            VideosViewPagerAdapter(requireActivity() as AppCompatActivity)
        TabLayoutMediator(binding.videosTabLayout, binding.videosViewPager) { tab, position ->
            tab.text = when (position) {
                else -> "مختارات"
            }
            tab.view.isClickable = false
        }.attach()
        binding.videosViewPager.isUserInputEnabled = false
    }
}