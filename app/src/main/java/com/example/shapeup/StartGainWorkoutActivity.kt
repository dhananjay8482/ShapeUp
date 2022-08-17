package com.example.shapeup

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import pl.droidsonroids.gif.GifImageView

class StartGainWorkoutActivity : AppCompatActivity() {

    private var exerciseTitles = arrayOf("Push Ups", "Bicycle Crunches", "Dead Bugarms", "Cross Sit Ups",
        "OverHead Crunches", "Esquire","Long Plank")

    private var exerciseDetail = arrayOf(
        "Do 20 pushups in 50 sec",
        "Do Bicycle Crunches for 30 sec ",
        "Do Dead Bugarms in 50 sec",
        "Do 12 Cross Sit Ups in 40 sec",
        "Do 15 Overhead Crunches in 80 sec",
        "Do 25 Esquire for 80 sec",
        "Do Long Plank for 60 sec"
    )

    private var exerciseImages = intArrayOf(
        R.drawable.push_up,
        R.drawable.bicycle_crunches,
        R.drawable.abs3,
        R.drawable.abs4,
        R.drawable.abs5,
        R.drawable.abs6,
        R.drawable.abs7
    )

    private var exerciseTime = intArrayOf(50, 30, 50, 40, 80, 80, 60)

    var pos = 0
    var timeLeftInMillis = 0

    var mediaPlayer: MediaPlayer? = null
    var mediaPlayer_workout: MediaPlayer? = null
    var mediaPlayer_rest: MediaPlayer? = null
    var counter_exercise = 0
    var counter_rest = 40

    var skip_exercise = false
    var skip_rest = false
    var is_timer = true
    var temp = "1"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_gain_workout)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //        Connecting Frontend with Variables
        val exercise_title = findViewById<TextView>(R.id.start_exercise_name)
        val exercise_desc = findViewById<TextView>(R.id.start_exercise_desc)
        val exercise_timer = findViewById<TextView>(R.id.start_exercise_timer)

        val exercise_gif = findViewById<GifImageView>(R.id.start_exercise_gif)

        val btn_skip_exercise = findViewById<Button>(R.id.btn_skip_ecercise)
        val btn_quit_exercise = findViewById<Button>(R.id.btn_quit_ecercise)
        val btn_quit1_exercise = findViewById<Button>(R.id.btn_quit_exercise)
        val btn_skip_rest = findViewById<Button>(R.id.btn_skip_rest)


//        Logic For Button Quit
        btn_quit_exercise.setOnClickListener {
            mediaPlayer_workout?.stop()
            mediaPlayer_rest?.stop()
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_quit1_exercise.setOnClickListener {
            mediaPlayer_workout?.stop()
            mediaPlayer_rest?.stop()
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        Setting Current Information related Exercise
        currentExercise()
        startTimer()

        btn_skip_exercise.setOnClickListener {
            skip_exercise = true
            is_timer = false
            breakTimer()

        }

    }

    private fun startTimer() {
        val exercise_timer = findViewById<TextView>(R.id.start_exercise_timer)
        val btn_skip_exercise = findViewById<Button>(R.id.btn_skip_ecercise)
        val btn_quit_exercise = findViewById<Button>(R.id.btn_quit_ecercise)
        val btn_skip_rest = findViewById<Button>(R.id.btn_skip_rest)

        var counter_exercise = 0
        counter_exercise = exerciseTime.get(pos)
        timeLeftInMillis = (exerciseTime.get(pos)*1000)+1100


        if(true){
//                  AUDIO PLAYER
            mediaPlayer_workout?.stop()
            mediaPlayer_rest?.stop()
            mediaPlayer_workout = MediaPlayer.create(this, R.raw.workout_shapeup)
            mediaPlayer_rest = MediaPlayer.create(this, R.raw.kbc_ringtone)
            mediaPlayer_workout?.start()
            mediaPlayer_workout?.setScreenOnWhilePlaying(true)
        }

        if(is_timer){
            object : CountDownTimer(timeLeftInMillis.toLong(), 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    if(skip_exercise) {
                        skip_exercise = false
                        cancel()
                    }else {
                        exercise_timer.text = counter_exercise.toString()
                        counter_exercise--
                    }
                }

                override fun onFinish() {
                    if ((!skip_exercise) && is_timer) {
//                        btn_skip.visibility = View.GONE
//                        btn_skip_rest.visibility = View.VISIBLE
                        breakTimer()

                    }
                }

            }.start()
        }


    }

    private fun breakTimer() {
        pos++
        counter_rest = 40

        val exercise = findViewById<LinearLayout>(R.id.startworkout_exercise)
        val rest = findViewById<LinearLayout>(R.id.startworkout_rest)

        val btn_skip_rest = findViewById<Button>(R.id.btn_skip_rest)

        exercise.visibility = View.GONE
        rest.visibility = View.VISIBLE

        if(true){
            mediaPlayer_workout?.stop()
            mediaPlayer_rest = MediaPlayer.create(this, R.raw.kbc_ringtone)
            mediaPlayer_rest?.start()
            mediaPlayer_rest?.setScreenOnWhilePlaying(true)

        }           // MediaPlayer


        btn_skip_rest.setOnClickListener {
            exercise.visibility = View.VISIBLE
            rest.visibility = View.GONE
            skip_rest = true
            skip_exercise = false
            is_timer = true
            mediaPlayer_rest?.stop()
            currentExercise()
            startTimer()
        }

        if(pos==7){
            mediaPlayer_rest?.stop()
            mediaPlayer_workout?.stop()

//            For next activity
            intent = Intent(this,FinalActivity::class.java)
            intent.putExtra("DAY",temp)
            startActivity(intent)
            finish()

        }
        else{
            val exercise_timer = findViewById<TextView>(R.id.start_rest_timer)

            object : CountDownTimer(41100, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    if(skip_rest){
                        skip_rest = false
                        cancel()
                    }
                    else{
                        exercise_timer.text = counter_rest.toString()
                        counter_rest--
                    }
                }

                override fun onFinish() {
                    exercise.visibility = View.VISIBLE
                    rest.visibility = View.GONE
                    skip_rest = false
                    skip_exercise = false
                    is_timer = true
                    mediaPlayer_rest?.stop()
                    currentExercise()
                    startTimer()
                }
            }.start()

        }





    }


    private fun currentExercise() {
//        It updartes every time as 'pos' variable increased

        val exercise_title = findViewById<TextView>(R.id.start_exercise_name)
        val exercise_desc = findViewById<TextView>(R.id.start_exercise_desc)
        val exercise_gif = findViewById<GifImageView>(R.id.start_exercise_gif)

        exercise_title.setText(exerciseTitles.get(pos))
        exercise_desc.setText(exerciseDetail.get(pos))
        exercise_gif.setImageResource(exerciseImages.get(pos))

    }

    override fun onBackPressed() {
        mediaPlayer_rest?.stop()
        mediaPlayer_workout?.stop()
        intent = Intent(this,MainActivity::class.java)
        finish()
    }




}