package com.moataz.afternoonhadeeth.ui.books.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.databinding.FragmentBooksTopBinding
import com.moataz.afternoonhadeeth.ui.books.adapter.BooksAdapter
import com.moataz.afternoonhadeeth.ui.books.viewmodel.BooksTopViewModel
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class BooksTopFragment : Fragment() {
    private var adapter = BooksAdapter()
    private val viewModel: BooksTopViewModel by viewModels()
    private lateinit var binding: FragmentBooksTopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksTopBinding.inflate(layoutInflater)
        initializeAdapter()
        getBooksList()
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

    private fun getBooksList() {
        viewModel.onResponse.observe(
            requireActivity()
        ) { response: Resource<BooksResponse> ->
            when (response.status) {
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setBooksList(response.data)
                }
            }
        }
    }
}