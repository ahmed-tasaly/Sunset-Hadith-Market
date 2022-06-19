package com.moataz.afternoonhadeeth.ui.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.FragmentPremiumOfflineBinding
import com.moataz.afternoonhadeeth.ui.home.view.fragment.HomeFragmentOffline
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed

class AboutFragmentOffline : Fragment(), IOnBackPressed {

    private lateinit var binding: FragmentPremiumOfflineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremiumOfflineBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onBackPressed(): Boolean {
        val mainFragment = HomeFragmentOffline()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout_offline, mainFragment, "findThisFragmentOffline")
            .addToBackStack(null)
            .commit()
        return true
    }
}