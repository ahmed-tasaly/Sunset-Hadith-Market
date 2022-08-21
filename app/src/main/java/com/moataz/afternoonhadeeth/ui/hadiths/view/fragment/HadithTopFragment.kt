package com.moataz.afternoonhadeeth.ui.hadiths.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import com.moataz.afternoonhadeeth.databinding.FragmentHadithTopBinding
import com.moataz.afternoonhadeeth.ui.hadiths.adapter.HadithAdapter
import com.moataz.afternoonhadeeth.ui.hadiths.view.activity.SearchHadithActivity
import com.moataz.afternoonhadeeth.ui.hadiths.viewmodel.HadithViewModel
import com.moataz.afternoonhadeeth.utils.helper.Intents
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class HadithTopFragment : Fragment() {

    private var adapter = HadithAdapter()
    private val viewModel: HadithViewModel by viewModels()
    private lateinit var binding: FragmentHadithTopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadithTopBinding.inflate(layoutInflater)
        setupEditText()
        getTopList()
        initializeAdapter()
        return binding.root
    }

    private fun setupEditText() {
        binding.editText.setOnClickListener {
            Intents.openNewActivity(requireContext(), SearchHadithActivity::class.java)
        }
        binding.editText.inputType = InputType.TYPE_NULL
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

    private fun getTopList() {
        viewModel.onResponse.observe(
            requireActivity()
        ) { response: Resource<HadithResponse> ->
            when (response.status) {
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setHadithList(response.data)
                }
            }
        }
    }
}