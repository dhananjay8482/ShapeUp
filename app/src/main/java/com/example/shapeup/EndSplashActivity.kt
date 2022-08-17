package com.example.shapeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class EndSplashActivity : AppCompatActivity() {

    //    SPLASH TIMER
    val SPLASH_SCREEN = 1100

    //    INITIALIZE VARIABLES
    lateinit var center : Animation
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        image = findViewById(R.id.shape_up_logo)
        center = AnimationUtils.loadAnimation(this,R.anim.end_anim)
        image.setAnimation(center)
        Handler(Looper.getMainLooper()).postDelayed({
            finish()

        },SPLASH_SCREEN.toLong())

    }
}