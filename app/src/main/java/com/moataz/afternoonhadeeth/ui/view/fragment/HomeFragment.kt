package com.moataz.afternoonhadeeth.ui.view.fragment

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.databinding.FragmentHomeBinding
import com.moataz.afternoonhadeeth.ui.adapter.HomeAdapter
import com.moataz.afternoonhadeeth.ui.view.activity.MainActivity
import com.moataz.afternoonhadeeth.ui.view.activity.MainOfflineActivity
import com.moataz.afternoonhadeeth.ui.viewmodel.HomeViewModel
import com.moataz.afternoonhadeeth.utils.helper.Intents.openUrl
import com.moataz.afternoonhadeeth.utils.helper.URL_Telegram_Channel
import com.moataz.afternoonhadeeth.utils.interfaces.CheckNetwork
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed
import com.moataz.afternoonhadeeth.utils.status.Resource
import com.moataz.afternoonhadeeth.utils.status.Status

class HomeFragment : BottomSheetDialogFragment() {

    private var adapter = HomeAdapter()
    private var viewModel = HomeViewModel()
    lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClickToolbarIcons()
        initializeAdapter()
        initializeViewModel()
        getTopList()
        showBottomSheetDialog()
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
        viewModel.makeApiCallHome().observe(requireActivity()
        ) { response: Resource<HomeResponse> ->
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
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(R.layout.bottomsheet_no_internet)
        if (CheckNetwork.isInternetAvailable(requireActivity())) {
            bottomSheetDialog.dismiss()
        } else {
            setupBottomSheetDialog()
        }
        /* Try Again Button */
        val buttonAgain = bottomSheetDialog.findViewById<Button>(R.id.buttonAgain)
        buttonAgain?.setOnClickListener {
            if (CheckNetwork.isInternetAvailable(requireActivity())) {
                bottomSheetDialog.dismiss()
                restart()
            } else {
                bottomSheetDialog.dismiss()
                restart()
            }
        }

        /* go to offline button */
        val buttonOffline = bottomSheetDialog.findViewById<Button>(R.id.buttonNoInternet)
        buttonOffline?.setOnClickListener {
            bottomSheetDialog.dismiss()
            val intent = Intent(context, MainOfflineActivity::class.java)
            this.startActivity(intent)
            bottomSheetDialog.isShowing
        }
    }

    private fun setupBottomSheetDialog() {
        bottomSheetDialog =
            object : BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme) {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setOnKeyListener { _: DialogInterface?, keyCode: Int, event: KeyEvent ->
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                            // Back key is pressed
                            bottomSheetDialog.dismiss() // Optional
                            requireActivity().moveTaskToBack(true) //exit the app when press back
                            requireActivity().finish()
                            return@setOnKeyListener true
                        }
                        true
                    }
                }
            }
        bottomSheetDialog.setContentView(R.layout.bottomsheet_no_internet)
        bottomSheetDialog.setCancelable(false)
    }

    private fun restart() {
        val intent = Intent(context, MainActivity::class.java)
        this.startActivity(intent)
        requireActivity().finishAffinity()
    }

    override fun onResume() {
        super.onResume()
        if (CheckNetwork.isInternetAvailable(requireActivity())) {
            bottomSheetDialog.dismiss()
        } else {
            bottomSheetDialog.show()
        }
    }
}