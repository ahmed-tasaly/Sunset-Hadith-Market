package com.moataz.afternoonhadeeth.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.databinding.FragmentImagesBinding
import com.moataz.afternoonhadeeth.ui.adapter.ImagesAdapter
import com.moataz.afternoonhadeeth.ui.viewmodel.ImagesViewModel
import com.moataz.afternoonhadeeth.utils.helper.Intents
import com.moataz.afternoonhadeeth.utils.helper.URL_Instagram_Account
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class ImagesFragment : Fragment(), IOnBackPressed {

    private var adapter = ImagesAdapter()
    private var viewModel = ImagesViewModel()
    private lateinit var binding: FragmentImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
        getTopList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        setOnClickToolbarIcons()
        initializeAdapter()
        return binding.root
    }

    private fun setOnClickToolbarIcons() {
        binding.instagram.setOnClickListener {
            Intents.openUrl((requireActivity() as AppCompatActivity), URL_Instagram_Account)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        // disable the touch on items when scroll the recyclerview
        binding.recyclerView.setOnTouchListener { _, motionEvent ->
            binding.recyclerView.onTouchEvent(motionEvent)
            true
        }
    }

    private fun getTopList() {
        viewModel.makeApiCallImages().observe(requireActivity(),
            { response: Resource<List<Images>> ->
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
        )
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
    }

    override fun onBackPressed(): Boolean {
        val mainFragment = HomeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, mainFragment, "findThisFragment")
            .addToBackStack(null)
            .commit()
        return true
    }
}