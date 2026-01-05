package com.abesamis.christmasgreetingapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var page0Intro: FrameLayout
    private lateinit var page1Santa: FrameLayout
    private lateinit var page2Loading: FrameLayout
    private lateinit var page3Message: FrameLayout

    private lateinit var staticSantaImage: ImageView
    private lateinit var santaImage: ImageView
    private lateinit var snowGifLoading: ImageView
    private lateinit var snowGifMessage: ImageView

    private lateinit var merryText: TextView
    private lateinit var christmasText: TextView
    private lateinit var greetingsText: TextView

    private lateinit var thankYouButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // the pages
        page0Intro = findViewById(R.id.page0Intro)
        page1Santa = findViewById(R.id.page1Santa)
        page2Loading = findViewById(R.id.page2Loading)
        page3Message = findViewById(R.id.page3Message)

        // the images and gifs
        staticSantaImage = findViewById(R.id.staticSantaImage)
        santaImage = findViewById(R.id.santaImage)
        snowGifLoading = findViewById(R.id.snowGifLoading)
        snowGifMessage = findViewById(R.id.snowGifMessage)

        // the text views
        merryText = findViewById(R.id.merryText)
        christmasText = findViewById(R.id.christmasText)
        greetingsText = findViewById(R.id.greetingsText)

        // the button
        thankYouButton = findViewById(R.id.thankYouButton)

        //load gifs
        loadSantaGif()
        loadSnowGifs()

        // Show the intro page initially
        showIntroPage()

        staticSantaImage.setOnClickListener {
            showSantaPage()
        }

        thankYouButton.setOnClickListener {
            showIntroPage()
        }
    }

    private fun showIntroPage() {
        page0Intro.visibility = View.VISIBLE
        page1Santa.visibility = View.GONE
        page2Loading.visibility = View.GONE
        page3Message.visibility = View.GONE
    }

    private fun showSantaPage() {
        page0Intro.visibility = View.GONE
        page1Santa.visibility = View.VISIBLE
        page2Loading.visibility = View.GONE
        page3Message.visibility = View.GONE

        // Delay for a few seconds and then show the loading page
        Handler(Looper.getMainLooper()).postDelayed({
            showLoadingPage()
        }, 3000) // 3 second delay
    }

    private fun loadSantaGif() {
    Glide.with(this)
        .asGif()
        .load("file:///android_asset/santa_sled.gif")
        .into(santaImage)
    }

    private fun loadSnowGifs() {
        Glide.with(this)
            .asGif()
            .load("file:///android_asset/snow.gif")
            .into(snowGifLoading)

        Glide.with(this)
            .asGif()
            .load("file:///android_asset/snow.gif")
            .into(snowGifMessage)
    }

    private fun showLoadingPage() {
        page1Santa.visibility = View.GONE
        page2Loading.visibility = View.VISIBLE
        page3Message.visibility = View.GONE

        merryText.alpha = 0f
        christmasText.alpha = 0f

        merryText.animate().alpha(1f).setDuration(800).start()
        christmasText.animate().alpha(1f).setDuration(800).setStartDelay(400).start()

        Handler(Looper.getMainLooper()).postDelayed({
            showMessagePage()
        }, 2500)
    }

    private fun showMessagePage() {
        page1Santa.visibility = View.GONE
        page2Loading.visibility = View.GONE
        page3Message.visibility = View.VISIBLE

        page3Message.alpha = 0f
        page3Message.animate()
            .alpha(1f)
            .setDuration(800)
            .start()
    }
}