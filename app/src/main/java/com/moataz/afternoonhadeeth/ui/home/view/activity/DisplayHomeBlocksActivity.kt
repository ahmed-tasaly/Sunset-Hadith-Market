package com.moataz.afternoonhadeeth.ui.home.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayHomeDataBlocksBinding
import com.moataz.afternoonhadeeth.ui.home.adapter.HomeBlocksAdapter
import com.moataz.afternoonhadeeth.utils.helper.Views

class DisplayHomeBlocksActivity : AppCompatActivity() {

    private var adapter =
        HomeBlocksAdapter()
    private lateinit var binding: ActivityDisplayHomeDataBlocksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayHomeDataBlocksBinding.inflate(layoutInflater)
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
        val title: String? = intent.getStringExtra("title")
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
        val infoList = extras?.getParcelableArrayList<DataList>("BlocksInfoList")
        adapter.setDataList(infoList)
        binding.recyclerView.adapter = adapter
    }

    private fun backToLastActivity() {
        finish()
    }
}