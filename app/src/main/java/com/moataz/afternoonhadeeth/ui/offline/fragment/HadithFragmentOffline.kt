package com.moataz.afternoonhadeeth.ui.offline.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.FragmentHadithOfflineBinding
import com.moataz.afternoonhadeeth.ui.offline.adapter.HadithOfflineAdapter
import com.moataz.afternoonhadeeth.ui.offline.viewmodel.HadithOfflineViewModel
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed

class HadithFragmentOffline : Fragment(), IOnBackPressed {

    private var adapter =
        HadithOfflineAdapter(
            requireContext()
        )
    private var viewModel = HadithOfflineViewModel()
    private lateinit var binding: FragmentHadithOfflineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadithOfflineBinding.inflate(layoutInflater)
        initializeViewModel()
        initializeAdapter()
        getHadithList()
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        // disable the touch on items when scroll the recyclerview
        binding.recyclerView.setOnTouchListener { _, motionEvent ->
            binding.recyclerView.onTouchEvent(motionEvent)
            true
        }
    }

    private fun getHadithList() {
        viewModel.hadithList.observe(
            requireActivity()
        ) { response ->
            adapter.setHadithList(response.data)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(HadithOfflineViewModel::class.java)
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