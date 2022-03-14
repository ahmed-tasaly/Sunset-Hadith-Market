package com.moataz.afternoonhadeeth.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moataz.afternoonhadeeth.databinding.FragmentImagesBinding
import com.moataz.afternoonhadeeth.ui.adapter.ImagesViewPagerAdapter

class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        binding.videosViewPager.adapter =
            ImagesViewPagerAdapter(requireActivity() as AppCompatActivity)
        TabLayoutMediator(binding.videosTabLayout, binding.videosViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "خلفيات"
                1 -> "الرسول الكريم"
                2 -> "المسجد النبوي"
                3 -> "الحرم المكي"
                else -> "المسجد الأقصى"
            }
        }.attach()
        binding.videosViewPager.offscreenPageLimit = 4
        binding.videosViewPager.isUserInputEnabled = false
    }
}