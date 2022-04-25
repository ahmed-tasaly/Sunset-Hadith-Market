package com.moataz.afternoonhadeeth.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.databinding.FragmentBooksHadithBinding
import com.moataz.afternoonhadeeth.ui.adapter.BooksAdapter
import com.moataz.afternoonhadeeth.ui.viewmodel.BooksHadithViewModel
import com.moataz.afternoonhadeeth.ui.viewmodel.BooksTopViewModel
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status


class BooksHadithFragment : Fragment() {
    private var adapter = BooksAdapter()
    private var viewModel = BooksHadithViewModel()
    private lateinit var binding: FragmentBooksHadithBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksHadithBinding.inflate(layoutInflater)
        initializeViewModel()
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
        viewModel.makeApiCallBooks().observe(
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

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(BooksHadithViewModel::class.java)
    }
}