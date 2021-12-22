package com.moataz.afternoonhadeeth.ui.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.FragmentPremiumBinding
import com.moataz.afternoonhadeeth.utils.helper.*

class PremiumFragment : Fragment(), BillingProcessor.IBillingHandler {

    private lateinit var binding: FragmentPremiumBinding
    private lateinit var billingProcess: BillingProcessor
    private var purchaseTransactionDetails: TransactionDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremiumBinding.inflate(layoutInflater)
        setupBillingProcess(context)
        openWebPages()
        return binding.root
    }

    private fun openWebPages() {
        binding.shareAppButton.setOnClickListener {
            Intents.sharedText(requireContext(), APP_URL, "حمل تطبيق حديث الغروب - وشارك أحاديث وسيرة النبي ﷺ")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!billingProcess.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setupBillingProcess(context: Context?) {
        billingProcess = BillingProcessor(
            context, resources.getString(R.string.play_console_license),
            this
        )
        billingProcess.initialize()
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        Log.d("MainActivity", "onProductPurchased: ")
    }

    override fun onPurchaseHistoryRestored() {
        Log.d("MainActivity", "onPurchaseHistoryRestored: ")
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        Log.d("MainActivity", "onBillingError: ")
    }

    override fun onBillingInitialized() {
        Log.d("MainActivity", "onBillingInitialized: ")
        val premium = resources.getString(R.string.premium)
        purchaseTransactionDetails = billingProcess.getSubscriptionTransactionDetails(premium)
        billingProcess.loadOwnedPurchasesFromGoogle()
    }

    override fun onDestroy() {
        billingProcess.release()
        super.onDestroy()
    }
}