package com.abesamis.christmasgreetingapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var page1Santa: FrameLayout
    private lateinit var page2Loading: FrameLayout
    private lateinit var page3Message: FrameLayout

    private lateinit var santaImage: ImageView
    private lateinit var snowGifLoading: ImageView
    private lateinit var snowGifMessage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // the pages
        page1Santa = findViewById(R.id.page1Santa)
        page2Loading = findViewById(R.id.page2Loading)
        page3Message = findViewById(R.id.page3Message)

        // the images and gifs
        santaImage = findViewById(R.id.santaImage)
        snowGifLoading = findViewById(R.id.snowGifLoading)
        snowGifMessage = findViewById(R.id.snowGifMessage)

        //load gifs
        loadSantaGif()
        loadSnowGifs()

        //animation
        startSantaAnimation()
    }

    private fun loadSantaGif() {
    Glide.with(this)
        .asGif()
        .load("file//android_asset/santa_sled.gif")
        .into(santaImage)
    }

    private fun loadSnowGifs() {
        Glide.with(this)
            .asGif()
            .load("file//androidn_assets/snow.gif")
            .into(snowGifLoading)

        Glide.with(this)
            .asGif()
            .load("file//androidn_assets/snow.gif")
            .into(snowGifMessage)
    }

    private fun startSantaAnimation() {
        santaImage.post {
            val screenWidth = resources.displayMetrics.widthPixels

            santaImage.animate()
                .translationX(screenWidth.toFloat() + 300)
                .setDuration(3500)
                .withEndAction {
                    showLoadingPage()
                }
                .start()
        }
    }

    private fun showLoadingPage() {
        page1Santa.visibility = View.GONE
        page2Loading.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            showMessagePage()
        }, 1500)
    }

    private fun showMessagePage() {
        page2Loading.visibility = View.GONE
        page3Message.visibility = View.VISIBLE

        page3Message.alpha = 0f
        page3Message.animate()
            .alpha(1f)
            .setDuration(800)
            .start()
    }
}