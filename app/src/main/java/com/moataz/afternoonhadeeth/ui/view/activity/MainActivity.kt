package com.moataz.afternoonhadeeth.ui.view.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.nativead.NativeAd
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityMainBinding
import com.moataz.afternoonhadeeth.ui.jetpack.notification.NotificationAfternoon
import com.moataz.afternoonhadeeth.ui.view.fragment.*
import com.moataz.afternoonhadeeth.ui.view.fragment.ImagesFragment
import com.moataz.afternoonhadeeth.utils.helper.Views
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed

class MainActivity : AppCompatActivity() {

    private val homeFragment: Fragment = HomeFragment()
    private val hadithFragment: Fragment = HadithFragment()
    private val imageFragment: Fragment = ImagesFragment()
    private val premiumFragment: Fragment = PremiumFragment()
    private var mainFragment = homeFragment
    private val fragmentManager = supportFragmentManager
    private lateinit var binding: ActivityMainBinding
    private val PERMISSION_REQUEST_CODE = 0

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
        setupNotification()
        setAdMob()
        initializeBottomNavigation()
    }
    private fun setAdMob() {
            MobileAds.initialize(
                this
            ) { initializationStatus: InitializationStatus? -> }
            val adRequest = AdRequest.Builder().build()
            binding.adView.loadAd(adRequest)
    }


    private fun initializeView() {
        Views.intiViews(window)
    }

    private fun setupNotification() {
        NotificationAfternoon().setupAfternoonNotification(this)
    }

    private fun initializeBottomNavigation() {
        // first one transaction to add each Fragment
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_layout, premiumFragment, "4").hide(premiumFragment)
        fragmentTransaction.add(R.id.fragment_layout, imageFragment, "3").hide(imageFragment)
        fragmentTransaction.add(R.id.fragment_layout, hadithFragment, "2").hide(hadithFragment)
        fragmentTransaction.add(R.id.fragment_layout, homeFragment, "1")
        // commit once! to finish the transaction
        fragmentTransaction.commit()

        // show and hide them when click on BottomNav items
        binding.bottomNavigation.itemRippleColor =
            ColorStateList.valueOf(Color.parseColor("#FFF5E6"))
        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            // start a new transaction
            val localFragmentTransaction = fragmentManager.beginTransaction()
            localFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            when (item.itemId) {
                R.id.home_item -> {
                    localFragmentTransaction.hide(mainFragment).show(homeFragment).commit()
                    mainFragment = homeFragment
                    return@setOnItemSelectedListener true
                }
                R.id.videos_item -> {
                    localFragmentTransaction.hide(mainFragment).show(hadithFragment).commit()
                    mainFragment = hadithFragment
                    return@setOnItemSelectedListener true
                }
                R.id.saved_item -> {
                    localFragmentTransaction.hide(mainFragment).show(imageFragment).commit()
                    mainFragment = imageFragment
                    return@setOnItemSelectedListener true
                }
                R.id.premium_item -> {
                    localFragmentTransaction.hide(mainFragment).show(premiumFragment).commit()
                    mainFragment = premiumFragment
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setHomeItemBack() {
        binding.bottomNavigation.selectedItemId = R.id.home_item
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_layout)
        if (fragment !is IOnBackPressed) {
            super.onBackPressed()
        }

        // Select the right bottom navigation when press back
        val selectedItemId = binding.bottomNavigation.selectedItemId
        if (R.id.home_item != selectedItemId) {
            setHomeItemBack()
        } else {
            super.onBackPressed()
        }
    }
}