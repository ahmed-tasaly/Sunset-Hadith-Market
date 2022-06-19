package com.moataz.afternoonhadeeth.ui.videos.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.databinding.FragmentVideosStarBinding

class VideosChoosenFragment : Fragment() {

    private lateinit var binding: FragmentVideosStarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosStarBinding.inflate(layoutInflater)
        return binding.root
    }
}