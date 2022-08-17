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

class SplashActivity : AppCompatActivity() {

//    SPLASH TIMER
    val SPLASH_SCREEN = 2300

//    INITIALIZE VARIABLES
    lateinit var center : Animation
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // TO Display Full Screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        DECLARE IMAGE VARIABLE
        image = findViewById(R.id.shape_up_logo)

//        DECLARE ANIMATION
        center = AnimationUtils.loadAnimation(this,R.anim.center_anim)

//      SET ANIMATION TO IMAGE
        image.setAnimation(center)


//        TIME HANDLER
        Handler(Looper.getMainLooper()).postDelayed({

//            INTENT set for setting path for next Activity
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        },SPLASH_SCREEN.toLong())


    }
}