package com.moataz.afternoonhadeeth.ui.videos.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.databinding.FragmentVideosTopBinding
import com.moataz.afternoonhadeeth.ui.videos.adapter.VideosTopBlocksAdapter
import com.moataz.afternoonhadeeth.ui.videos.adapter.VideosTopListAdapter
import com.moataz.afternoonhadeeth.ui.videos.viewmodel.VideosTopViewModel
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class VideosTopFragment : Fragment() {

    private lateinit var binding: FragmentVideosTopBinding
    private var blocksAdapter = VideosTopBlocksAdapter()
    private var listAdapter = VideosTopListAdapter()
    private val concatAdapter = ConcatAdapter(blocksAdapter, listAdapter)
    private val viewModel: VideosTopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosTopBinding.inflate(layoutInflater)
        initializeAdapter()
        getTopList()
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = concatAdapter
            setOnTouchListener { _, motionEvent ->
                binding.recyclerView.onTouchEvent(motionEvent)
                true
            }
        }
    }

    private fun getTopList() {
        viewModel.onResponse.observe(
            requireActivity()
        ) { response: Resource<TopVideosResponse> ->
            when (response.status) {
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    blocksAdapter.setTopVideosBlocks(response.data)
                    listAdapter.setTopVideosList(response.data!!)
                }
            }
        }
    }
}