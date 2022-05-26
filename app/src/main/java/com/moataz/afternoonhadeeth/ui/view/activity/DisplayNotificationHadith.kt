package com.moataz.afternoonhadeeth.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayNotificationBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents
import com.moataz.afternoonhadeeth.utils.helper.Views

class DisplayNotificationHadith : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNotificationBinding.inflate(layoutInflater)
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
        val hadith: String? = intent.getStringExtra("hadithNotification")
        val title: String? = intent.getStringExtra("titleNotification")
        binding.title.text = title
        binding.hadith.text = hadith

        binding.back.setOnClickListener {
            finish()
        }

        binding.share.setOnClickListener {
            Intents.copyText(hadith!!, this, "")
            Intents.sharedText(this, hadith, "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ", "")
        }

        binding.copy.setOnClickListener {
            Intents.copyText(hadith!!, this, "")
            Intents.showToast(this, "تم نسخ الحديث")
        }
    }
}