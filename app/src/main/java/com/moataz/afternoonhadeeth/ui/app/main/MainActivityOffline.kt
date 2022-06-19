package com.moataz.afternoonhadeeth.ui.app.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityMainOfflineBinding
import com.moataz.afternoonhadeeth.ui.hadiths.view.fragment.HadithFragmentOffline
import com.moataz.afternoonhadeeth.ui.home.view.fragment.HomeFragmentOffline
import com.moataz.afternoonhadeeth.ui.books.view.fragment.BooksFragmentOffline
import com.moataz.afternoonhadeeth.utils.helper.Views
import com.moataz.afternoonhadeeth.utils.interfaces.IOnBackPressed

class MainActivityOffline : AppCompatActivity() {

    private lateinit var binding: ActivityMainOfflineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainOfflineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
        initializeBottomNavigation()
    }

    private fun initializeView() {
        Views.intiViews(window)
        window.navigationBarColor = resources.getColor(R.color.black_overlay);
    }

    private fun initializeBottomNavigation() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_layout_offline,
            HomeFragmentOffline()
        ).commit()
        binding.bottomNavigation.itemRippleColor =
            ColorStateList.valueOf(Color.parseColor("#FFF5E6"))
        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.home_item_offline -> {
                    selectedFragment = HomeFragmentOffline()
                }
                R.id.hadith_item_library_offline -> {
                    selectedFragment = BooksFragmentOffline()
                }
                R.id.hadith_item_offline -> {
                    selectedFragment = HadithFragmentOffline()
                }
                R.id.about_item_offline -> {
                    selectedFragment = AboutFragmentOffline()
                }
            }
            assert(selectedFragment != null)
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_layout_offline,
                selectedFragment!!
            ).commit()
            true
        }
    }

    private fun setHomeItemBack() {
        binding.bottomNavigation.selectedItemId = R.id.home_item_offline
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_layout_offline)
        if (fragment !is IOnBackPressed) {
            super.onBackPressed()
        }

        // Select the right bottom navigation when press back
        val selectedItemId = binding.bottomNavigation.selectedItemId
        if (R.id.home_item_offline != selectedItemId) {
            setHomeItemBack()
        } else {
            super.onBackPressed()
        }
    }
}