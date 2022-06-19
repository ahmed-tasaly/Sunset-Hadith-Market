package com.moataz.afternoonhadeeth.ui.hadiths.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.databinding.FragmentHadithBinding
import com.moataz.afternoonhadeeth.ui.hadiths.adapter.HadithViewPagerAdapter

class HadithFragment : Fragment() {

    private lateinit var binding: FragmentHadithBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadithBinding.inflate(layoutInflater)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        binding.videosViewPager.adapter =
            HadithViewPagerAdapter(requireActivity() as AppCompatActivity)
        binding.videosViewPager.isUserInputEnabled = false
    }
}