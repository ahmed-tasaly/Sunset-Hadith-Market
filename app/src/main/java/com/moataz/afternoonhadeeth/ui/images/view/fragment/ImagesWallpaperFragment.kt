package com.moataz.afternoonhadeeth.ui.images.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.databinding.FragmentImagesWallpaperBinding
import com.moataz.afternoonhadeeth.ui.images.adapter.ImagesWallpaperAdapter
import com.moataz.afternoonhadeeth.ui.images.viewmodel.ImagesWallpaperViewModel
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class ImagesWallpaperFragment : Fragment() {
    private var adapter =
        ImagesWallpaperAdapter()
    private var viewModel = ImagesWallpaperViewModel()
    private lateinit var binding: FragmentImagesWallpaperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesWallpaperBinding.inflate(layoutInflater)
        initializeViewModel()
        initializeAdapter()
        getTopList()
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        // disable the touch on items when scroll the recyclerview
        binding.recyclerView.setOnTouchListener { _, motionEvent ->
            binding.recyclerView.onTouchEvent(motionEvent)
            true
        }
    }

    private fun getTopList() {
        viewModel.makeApiCallImages().observe(
            requireActivity()
        ) { response: Resource<List<Images>> ->
            when (response.status) {
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setImagesList(response.data)
                }
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(ImagesWallpaperViewModel::class.java)
    }
}