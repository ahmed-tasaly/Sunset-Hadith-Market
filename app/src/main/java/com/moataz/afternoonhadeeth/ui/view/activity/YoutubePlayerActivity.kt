package com.moataz.afternoonhadeeth.ui.view.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.moataz.afternoonhadeeth.data.api.APIYoutubeKey
import com.moataz.afternoonhadeeth.databinding.ActivityYoutubePlayerBinding

class YoutubePlayerActivity : YouTubeBaseActivity() {

    private var binding: ActivityYoutubePlayerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubePlayerBinding.inflate(
            layoutInflater
        )
        val view: View = binding!!.root
        setContentView(view)
        playYoutubeVideo()
        initializeViews()
    }

    private fun initializeViews() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) // hide Status bar
    }

    private fun playYoutubeVideo() {
        binding!!.youtubePlayerViewHome.initialize(
            APIYoutubeKey.API_YOUTUBE_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    val videoId = intent.extras!!.getString("url")
                    youTubePlayer.loadVideo(videoId, 0)
                    youTubePlayer.setFullscreen(true)
                    youTubePlayer.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Toast.makeText(
                        applicationContext,
                        "خطأ في التشغيل، يرجى إعادة المحاولة",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}