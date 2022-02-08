package com.moataz.afternoonhadeeth.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.databinding.FragmentVideosBinding

class VideosFragment : Fragment() {

    private lateinit var binding: FragmentVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosBinding.inflate(layoutInflater)
        return binding.root
    }
}
