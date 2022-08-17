package com.example.shapeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {

//    Declaring buttons
    lateinit var btn_start:Button
    lateinit var btn_tips:Button
    lateinit var btn_diet:Button
    lateinit var btn_about:Button
    lateinit var btn_bmi:Button
    lateinit var btn_gain:Button
    lateinit var btn_abs:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // TO Display Full Screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        Assigning Buttons to front end
        btn_start = findViewById(R.id.btn_start)
        btn_abs = findViewById(R.id.btn_abs)
        btn_gain = findViewById(R.id.btn_gain)
        btn_tips = findViewById(R.id.btn_tips)
        btn_diet = findViewById(R.id.btn_diet)
        btn_about = findViewById(R.id.btn_about)
        btn_bmi = findViewById(R.id.btn_bmi)

//        Setting OnClick Listener For Each Button
//        Where we create New Slide for Each new Button on Click call

        btn_start.setOnClickListener {
            intent = Intent(this,IndexActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_about.setOnClickListener {
            intent = Intent(this,AboutActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_diet.setOnClickListener {
            intent = Intent(this,DietActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_tips.setOnClickListener {
            intent = Intent(this,TipsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_bmi.setOnClickListener {
            intent = Intent(this,BMICalculatorActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_gain.setOnClickListener {
            intent = Intent(this,GainIndexAcivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_abs.setOnClickListener {
            intent = Intent(this,AbsIndexAcivity::class.java)
            startActivity(intent)
            finish()
        }
    }

//    To Clear the Activity Stack

    override fun onBackPressed() {
        intent = Intent(this,EndSplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}