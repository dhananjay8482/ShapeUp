package com.example.shapeup

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import pl.droidsonroids.gif.GifImageView
import kotlin.properties.Delegates

class WorkoutPlanActivity : AppCompatActivity() {

    var passing_day = "0"

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        Received Extra Value by Index Activty
        val day = intent.getStringExtra("DAY")
        passing_day = day.toString()

//        Connecting Fronend Textview and Gif View
        val exer1 = findViewById<TextView>(R.id.my_exercise_text_1)
        val exer2 = findViewById<TextView>(R.id.my_exercise_text_2)
        val exer3 = findViewById<TextView>(R.id.my_exercise_text_3)
        val exer4 = findViewById<TextView>(R.id.my_exercise_text_4)

        val exer_gif_1 = findViewById<GifImageView>(R.id.my_exercise_1)
        val exer_gif_2 = findViewById<GifImageView>(R.id.my_exercise_2)
        val exer_gif_3 = findViewById<GifImageView>(R.id.my_exercise_3)
        val exer_gif_4 = findViewById<GifImageView>(R.id.my_exercise_4)


//      Connecting Button With Front end Button
        val btn_start = findViewById<Button>(R.id.btn_plan_start)

        if(passing_day == "2" || passing_day == "5"){

//            Changeing only middle 4 Exercise Accordong to Day

            exer1.text = "Mountain\nClimber"
            exer_gif_1.setImageResource(R.drawable.mountain_climber)

            exer2.text = "Burpees"
            exer_gif_2.setImageResource(R.drawable.burpees)

            exer3.text = "Russian\nTwist"
            exer_gif_3.setImageResource(R.drawable.russian_twist)

            exer4.text = "Toe\nTouch"
            exer_gif_4.setImageResource(R.drawable.toe_touch)

        }

        if(passing_day == "3" || passing_day == "6"){

//            Changeing only middle 4 Exercise Accordong to Day

            exer1.text = "Bicycle\nCrunches"
            exer_gif_1.setImageResource(R.drawable.bicycle_crunches)

            exer2.text = "Pelvic\nBridge"
            exer_gif_2.setImageResource(R.drawable.pelvic_bridge)

            exer3.text = "Bird\nDog"
            exer_gif_3.setImageResource(R.drawable.bird_dog)

            exer4.text = "Leg\nKick"
            exer_gif_4.setImageResource(R.drawable.leg_raise)

        }


        btn_start.setOnClickListener {
            intent = Intent(this, StartWorkoutActivity::class.java)
            intent.putExtra("DAY",passing_day)
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

