package com.moataz.afternoonhadeeth.ui.books.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moataz.afternoonhadeeth.databinding.FragmentLibraryBinding
import com.moataz.afternoonhadeeth.ui.home.view.activity.PDFActivity

class BooksFragmentOffline : Fragment() {

    private lateinit var binding: FragmentLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        openPDFView()
        return binding.root
    }

    private fun openPDFView() {
        binding.buttonLib.setOnClickListener {
            val intent = Intent(context, PDFActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }
}