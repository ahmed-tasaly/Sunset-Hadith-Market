package com.moataz.afternoonhadeeth.ui.hadiths.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayHadithsBinding
import com.moataz.afternoonhadeeth.ui.hadiths.adapter.HadithListAdapter
import com.moataz.afternoonhadeeth.utils.helper.Views

class DisplayHadithListActivity : AppCompatActivity() {

    private var adapter =
        HadithListAdapter()
    private lateinit var binding: ActivityDisplayHadithsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayHadithsBinding.inflate(layoutInflater)
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
        val title: String? = intent.getStringExtra("hadithTitle")
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
        val hadithList = extras?.getParcelableArrayList<Hadith>("hadithList")
        adapter.setHadithList(hadithList)
        binding.recyclerView.adapter = adapter
    }

    private fun backToLastActivity() {
        finish()
    }
}