package com.example.shapeup

import android.annotation.SuppressLint
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

class StartWorkoutActivity : AppCompatActivity() {

//    Setting Bydefault Day 1 Exercise

    private var exerciseTitles = arrayOf("Jumping Jacks", "High Stepping", "Push Up", "Sit Up Crunches",
        "Squats", "Leg Raises","Hip Did", "Plank","Cobra Stretch")

    private var exerciseDetail = arrayOf(
        "Do Jumping jacks 50 sec",
        "Do High stepping for 30 sec ",
        "Do 15 Push Up in 50 sec",
        "Do 12 Sit Up Crunches in 40 sec",
        "Do 15 Rev-Crunches in 80 sec",
        "Do 10 Leg Raises for 80 sec",
        "Do 10 Hip Did for 60 sec",
        "Do plank for 150 sec",
        "Do Cobra Stretching for 150 sec"

    )

    private var exerciseImages = intArrayOf(
        R.drawable.jumping_jacks,
        R.drawable.high_stepping,
        R.drawable.push_up,
        R.drawable.sit_up_crunches,
        R.drawable.squats,
        R.drawable.leg_raise,
        R.drawable.hip_did,
        R.drawable.plank,
        R.drawable.cobra_stretch
    )

    private var exerciseTime = intArrayOf(50, 30, 50, 40, 80, 80, 60,150,150)
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

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        Received Extra Value by Index Activty
        val day = intent.getStringExtra("DAY")
        temp = intent.getStringExtra("DAY").toString()
        val passing_day = day.toString()


//        CHAINGING EXERCISE,TITLE, DESC ARRAY AS PER OUR WORKOUT PLAN

//        for Tuesday and Friday
        if(passing_day == "2" || passing_day == "5"){
            exerciseTitles = arrayOf("Jumping Jacks", "High Stepping", "Push Up", "Mountain Climber",
                "Burpees", "Russian Twist","Toe touch", "Plank","Cobra Stretch")

            exerciseDetail = arrayOf(
                "Do Jumping jacks 50 sec",
                "Do High stepping for 30 sec ",
                "Do 15 Push Up in 50 sec",
                "Do Mountain Climber for 60 sec",
                "Do 10 Burpees in 70 sec",
                "Do 10 Russian Twist (Each Side) in 50 sec",
                "Do 10 Toe Touch for 40 sec",
                "Do plank for 150 sec",
                "Do Cobra Stretching for 150 sec"

            )

            exerciseTime = intArrayOf(50, 30, 50, 60, 70, 50, 40,150,150)

            exerciseImages = intArrayOf(
                R.drawable.jumping_jacks,
                R.drawable.high_stepping,
                R.drawable.push_up,
                R.drawable.mountain_climber,
                R.drawable.burpees,
                R.drawable.russian_twist,
                R.drawable.toe_touch,
                R.drawable.plank,
                R.drawable.cobra_stretch
            )


        }

//       for Tuesday and Friday
        if(passing_day == "3" || passing_day == "6"){
            exerciseTitles = arrayOf("Jumping Jacks", "High Stepping", "Push Up", "Bicycle Crunches",
                "Pelvic Bridge", "Bird Dog","Leg Raise", "Plank","Cobra Stretch")

            exerciseDetail = arrayOf(
                "Do Jumping jacks 50 sec",
                "Do High stepping for 30 sec ",
                "Do 15 Push Up in 50 sec",
                "Do 10 Bicycle Crunches (Each Side) for 60 sec",
                "Do 12 Pelvic Bridge in 50 sec",
                "Do 10 Bird Dog (Each Side) in 60 sec",
                "Do 10 Leg Raise for 40 sec",
                "Do plank for 150 sec",
                "Do Cobra Stretching for 150 sec"

            )

            exerciseTime = intArrayOf(50, 30, 50, 60, 50, 60, 40,150,150)

            exerciseImages = intArrayOf(
                R.drawable.jumping_jacks,
                R.drawable.high_stepping,
                R.drawable.push_up,
                R.drawable.bicycle_crunches,
                R.drawable.pelvic_bridge,
                R.drawable.bird_dog,
                R.drawable.leg_raise,
                R.drawable.plank,
                R.drawable.cobra_stretch
            )


        }

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

        if(pos==9){
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