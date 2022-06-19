package com.moataz.afternoonhadeeth.ui.books.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayBookBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents
import com.moataz.afternoonhadeeth.utils.helper.Views

class DisplayBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiView()
        intiToolbar()
        intiData()
    }

    private fun intiView() {
        Views.intiViews(window)
        window.navigationBarColor = resources.getColor(R.color.black_overlay)
    }

    private fun intiToolbar() {
        binding.backIcon.setOnClickListener { backToLastActivity() }
    }

    private fun intiData() {
        binding.bookTitle.text = intent.getStringExtra("bookTitle")
        binding.bookAuthor.text = intent.getStringExtra("bookAuthor")
        binding.ratingBar.rating = 5f
        binding.bookDetails.text = intent.getStringExtra("bookDetails")

        loadImageFromUrl(intent.getStringExtra("bookImage").toString())
        binding.downloadBook.setOnClickListener { downloadBookFromUrl() }
        binding.downloadBookText.setOnClickListener { downloadBookFromUrl() }
    }

    private fun loadImageFromUrl(url: String) {
        binding.bookImage.load(url) {
            crossfade(true)
            placeholder(R.drawable.folder_loading_image)
        }
    }

    private fun downloadBookFromUrl() {
        Intents.openTabUrl(this, intent.getStringExtra("bookUrl")?.toUri().toString())
    }

    private fun backToLastActivity() {
        finish()
    }
}