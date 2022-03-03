package com.moataz.afternoonhadeeth.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.databinding.FragmentPremiumBinding
import com.moataz.afternoonhadeeth.utils.helper.*

class PremiumFragment : Fragment() {

    private lateinit var binding: FragmentPremiumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremiumBinding.inflate(layoutInflater)
        openWebPages()
        return binding.root
    }

    private fun openWebPages() {
        binding.shareAppButton.setOnClickListener {
            Intents.sharedText(
                requireContext(),
                APP_URL,
                "حمل تطبيق حديث الغروب - وشارك أحاديث وسيرة النبي ﷺ", ""
            )
        }

        binding.followUsOnFacebookButton.setOnClickListener {
            Intents.openUrl((requireActivity() as AppCompatActivity), URL_FACEBOOK_PAGE)
        }
        binding.joinUsInTelegramButton.setOnClickListener {
            Intents.openUrl((requireActivity() as AppCompatActivity), URL_Telegram_Channel)
        }
        binding.followUsOnTwitterButton.setOnClickListener {
            Intents.openUrl((requireActivity() as AppCompatActivity), URL_Twitter_Account)
        }
    }
}