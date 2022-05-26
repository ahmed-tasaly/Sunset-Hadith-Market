package com.moataz.afternoonhadeeth.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.videos.top.Data
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayVideosDataBlocksBinding
import com.moataz.afternoonhadeeth.ui.adapter.VideosTopBlocksListAdapter
import com.moataz.afternoonhadeeth.utils.helper.Views

class DisplayVideoBlocksActivity : AppCompatActivity() {

    private var adapter =
        VideosTopBlocksListAdapter()
    private lateinit var binding: ActivityDisplayVideosDataBlocksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayVideosDataBlocksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiView()
        intiToolbar()
        intiAdapter()
        getList()
    }

    private fun intiView() {
        Views.intiViews(window)
        window.navigationBarColor = resources.getColor(R.color.card_color)
    }

    private fun intiToolbar() {
        val intent: Intent = intent
        val title: String? = intent.getStringExtra("topVideosTitle")
        binding.topText.text = title
        binding.backToLastActivity.setOnClickListener { backToLastActivity() }
    }

    private fun intiAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun getList() {
        val intent: Intent = intent
        val extras = intent.extras
        val infoList = extras?.getParcelableArrayList<Data>("topVideosBlocksList")
        adapter.setDataList(infoList)
        binding.recyclerView.adapter = adapter
    }

    private fun backToLastActivity() {
        finish()
    }
}