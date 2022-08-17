package com.example.shapeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class AbsIndexAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_index_acivity)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val btn_start = findViewById<Button>(R.id.btn_plan_abs_start)

        btn_start.setOnClickListener {
            intent = Intent(this, StartAbsWorkoutActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
    override fun onBackPressed() {
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}