package com.moataz.afternoonhadeeth.ui.books.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moataz.afternoonhadeeth.databinding.FragmentBooksBinding
import com.moataz.afternoonhadeeth.ui.books.adapter.BooksViewPagerAdapter

class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksBinding.inflate(layoutInflater)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        binding.videosViewPager.adapter =
            BooksViewPagerAdapter(requireActivity() as AppCompatActivity)
        TabLayoutMediator(binding.videosTabLayout, binding.videosViewPager) { tab, position ->
            tab.text = when (position) {
                else -> "السيرة"
            }
        }.attach()
        binding.videosViewPager.isUserInputEnabled = false
    }
}