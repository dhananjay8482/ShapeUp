package com.example.shapeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class IndexActivity : AppCompatActivity() {

//    Temperory Variable For passing Day Value to Another Activity
    var temp = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


//        Button Initialization and Declaration

        val btn_sunday = findViewById<Button>(R.id.btn_index_sunday)
        val btn_monday = findViewById<Button>(R.id.btn_index_monday)
        val btn_tuesday = findViewById<Button>(R.id.btn_index_tuesday)
        val btn_wednesday = findViewById<Button>(R.id.btn_index_wednesday)
        val btn_thursday = findViewById<Button>(R.id.btn_index_thursday)
        val btn_friday = findViewById<Button>(R.id.btn_index_friday)
        val btn_saturday = findViewById<Button>(R.id.btn_index_saturday)
        val btn_back = findViewById<Button>(R.id.btn_index_back)


//        Setting OnClick Listener Method to Each of Them.

        btn_monday.setOnClickListener {
            temp=1  // 1 For Monday So We can pass 1 value to next activity to Identify Which day is selected

            intent = Intent(this, WorkoutPlanActivity::class.java)

            intent.putExtra("DAY",temp.toString())

            startActivity(intent)

            finish()

        }

        btn_tuesday.setOnClickListener {
            temp=2
            intent = Intent(this,WorkoutPlanActivity::class.java)
            intent.putExtra("DAY",temp.toString())
            startActivity(intent)
            finish()
        }

        btn_wednesday.setOnClickListener {
            temp=3
            intent = Intent(this,WorkoutPlanActivity::class.java)
            intent.putExtra("DAY",temp.toString())
            startActivity(intent)
            finish()
        }

        btn_thursday.setOnClickListener {
            temp=4
            intent = Intent(this,WorkoutPlanActivity::class.java)
            intent.putExtra("DAY",temp.toString())
            startActivity(intent)
            finish()
        }

        btn_friday.setOnClickListener {
            temp=5
            intent = Intent(this,WorkoutPlanActivity::class.java)
            intent.putExtra("DAY",temp.toString())
            startActivity(intent)
            finish()
        }

        btn_saturday.setOnClickListener {
            temp=6
            intent = Intent(this,WorkoutPlanActivity::class.java)
            intent.putExtra("DAY",temp.toString())
            startActivity(intent)
            finish()
        }

        btn_sunday.setOnClickListener {
            temp=7
//            intent = Intent(this,WorkoutPlanActivity::class.java)
//            intent.putExtra("DAY",temp.toString())
//            startActivity(intent)
            Toast.makeText(this, "Break Day !", Toast.LENGTH_SHORT).show()
        }

        btn_back.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
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