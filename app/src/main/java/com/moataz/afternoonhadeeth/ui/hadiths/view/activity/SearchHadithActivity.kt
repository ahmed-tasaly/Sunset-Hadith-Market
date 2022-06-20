package com.moataz.afternoonhadeeth.ui.hadiths.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivitySearchHadithBinding


class SearchHadithActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchHadithBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchHadithBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        webView()
    }

    private fun initView() {
        window.navigationBarColor = resources.getColor(R.color.black_overlay)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window.statusBarColor = this.resources.getColor(R.color.white)
    }

    private fun webView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.dorar.net/hadith/search?q=%D8%B5%D8%AF%D9%82%D8%A9&st=w&xclude=&t=*&d%5B%5D=1&d%5B%5D=2&rawi%5B%5D=&fillopts=on")
        binding.webView.webViewClient = WebViewClient()
    }
}