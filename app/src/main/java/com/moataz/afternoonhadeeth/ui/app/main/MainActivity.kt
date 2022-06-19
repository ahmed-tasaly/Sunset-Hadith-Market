package com.moataz.afternoonhadeeth.ui.app.main

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.messaging.FirebaseMessaging
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityMainBinding
import com.moataz.afternoonhadeeth.ui.app.notification.activity.DisplayNotificationActivity
import com.moataz.afternoonhadeeth.ui.app.notification.schedulie.AfternoonNotification
import com.moataz.afternoonhadeeth.ui.app.notification.schedulie.MorningNotification
import com.moataz.afternoonhadeeth.ui.app.notification.schedulie.NightNotification
import com.moataz.afternoonhadeeth.utils.helper.Views
import com.suddenh4x.ratingdialog.AppRating
import com.suddenh4x.ratingdialog.preferences.RatingThreshold

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
        setupNotification()
        initializeBottomNavigation()
        inAppUpdate()
        inAppRating()
        firebaseDeviceToken()
        if (intent.extras != null) {
            val notificationIntent = Intent(this, DisplayNotificationActivity::class.java)
            // get the data from the Firebase notification payload received in the intent extras bundle and put it in the notificationIntent intent extras bundle to be used in the DisplayNotificationActivity class
            notificationIntent.putExtra("titleNotification", intent.extras!!.getString("titleNotification"))
            notificationIntent.putExtra("hadithNotification", intent.extras!!.getString("hadithNotification"))
            startActivity(notificationIntent)
        }
    }

    private fun initializeView() {
        Views.intiViews(window)
        window.navigationBarColor = resources.getColor(R.color.black_overlay)
    }

    private fun setupNotification() {
        MorningNotification().setupMorningNotification(this)
        NightNotification().setupNightNotification(this)
        AfternoonNotification().setupAfternoonNotification(this)
    }

    private fun initializeBottomNavigation() {
        binding.bottomNavigation.itemRippleColor =
            ColorStateList.valueOf(Color.parseColor("#FFF5E6"))

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun inAppRating() {
        AppRating.Builder(this)
            .setMinimumLaunchTimes(3)
            .setMinimumDays(3)
            .useGoogleInAppReview()
            .setMinimumLaunchTimesToShowAgain(10)
            .setMinimumDaysToShowAgain(7)
            .setRatingThreshold(RatingThreshold.FOUR)
            .showIfMeetsConditions()
    }

    private fun inAppUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && (appUpdateInfo.clientVersionStalenessDays() ?: -1) >= 3
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    0
                )
            }
        }
    }

    private fun firebaseDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("MainActivity", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("MainActivity", token)
        })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode != RESULT_OK) {
                Log.e("MY_APP", "Update flow failed! Result code: $resultCode")
                inAppUpdate()
            }
        }
    }
}