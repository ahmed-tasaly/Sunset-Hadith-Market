package com.moataz.afternoonhadeeth.ui.view.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayHadithListBinding
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayNotificationHadithBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents
import com.moataz.afternoonhadeeth.utils.helper.Views

class DisplayNotificationHadith : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayNotificationHadithBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNotificationHadithBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiView()
        intiText()
    }

    private fun intiView() {
        Views.intiViews(window)
        window.navigationBarColor = resources.getColor(R.color.card_color)
    }

    private fun intiText() {
        val intent: Intent = intent
        val hadith: String?  = intent.getStringExtra("hadithNotification")
        val title: String?  = intent.getStringExtra("titleNotification")
        binding.title.text = title
        binding.hadith.text = hadith

        binding.back.setOnClickListener {
            finish()
        }

        binding.share.setOnClickListener {
            Intents.copyText( hadith!!, this,"")
            Intents.sharedText(this, hadith, "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ", "")
        }

        binding.copy.setOnClickListener {
            Intents.copyText( hadith!!, this,"")
            Intents.showToast(this, "تم نسخ الحديث")
        }
    }
}