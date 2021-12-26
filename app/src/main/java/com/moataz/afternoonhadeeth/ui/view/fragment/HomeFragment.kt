package com.moataz.afternoonhadeeth.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.databinding.FragmentHomeBinding
import com.moataz.afternoonhadeeth.ui.adapter.HomeAdapter
import com.moataz.afternoonhadeeth.ui.viewmodel.HomeViewModel
import com.moataz.afternoonhadeeth.utils.helper.Intents.openUrl
import com.moataz.afternoonhadeeth.utils.helper.URL_Telegram_Channel
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class HomeFragment : Fragment(), IOnBackPressed {

    private var adapter = HomeAdapter()
    private var viewModel = HomeViewModel()
    private lateinit var binding: FragmentHomeBinding

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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClickToolbarIcons()
        initializeAdapter()
        return binding.root
    }

    private fun setOnClickToolbarIcons() {
        binding.instagram.setOnClickListener {
            openUrl((requireActivity() as AppCompatActivity), URL_Telegram_Channel)
        }
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
        viewModel.makeApiCallHome().observe(requireActivity(),
            { response: Resource<HomeResponse> ->
                when (response.status) {
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setHomeList(response.data)
                    }
                }
            }
        )
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onBackPressed(): Boolean {
        requireActivity().moveTaskToBack(true)
        requireActivity().finish()
        return true
    }

}